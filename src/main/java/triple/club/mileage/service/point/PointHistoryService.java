package triple.club.mileage.service.point;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.club.mileage.domain.Place;
import triple.club.mileage.domain.PointHistory;
import triple.club.mileage.domain.Review;
import triple.club.mileage.domain.User;
import triple.club.mileage.domain.enums.PointEventType;
import triple.club.mileage.repository.PointHistoryRepository;

@Service
@RequiredArgsConstructor
public class PointHistoryService {
    private final PointHistoryRepository pointHistoryRepository;

    public long reviewAdd(Review review, Place place, User user) {
        long pointScore = 0L;
        if (review.checkContent()) {
            pointScore += savePointHistory(PointEventType.REVIEW_CONTENT, user);
        }
        if (review.checkPhotos()) {
            pointScore += savePointHistory(PointEventType.REVIEW_PHOTO, user);
        }
        if (place.isZeroReview()) {
            pointScore += savePointHistory(PointEventType.PLACE_FIRST_REVIEW, user);
            review.changeFirstReviewStatus(true);
            place.changeReviewState(false);
        }
        return pointScore;
    }

    public long reviewDelete(Review review, Place place, User user) {
        long pointScore = 0L;
        if (review.checkContent()) {
            pointScore += savePointHistory(PointEventType.REVIEW_CONTENT_ZERO, user);
        }
        if (review.checkPhotos()) {
            pointScore += savePointHistory(PointEventType.REVIEW_PHOTO_ZERO, user);
        }
        if (!place.isZeroReview() && review.isFirstReview()) {
            pointScore += savePointHistory(PointEventType.PLACE_FIRST_REVIEW_DELETE, user);
        }

        if (place.getReviews().size() - 1 == 0)
            place.changeReviewState(true);

        return pointScore;
    }

    public long reviewMod(Review currReview, Review modifyReview, Place place, User user) {
        long pointScore = 0L;
        if (!modifyReview.checkContent() && currReview.checkContent()) {
            pointScore += savePointHistory(PointEventType.REVIEW_CONTENT_ZERO, user);
        } else if (modifyReview.checkContent() && !currReview.checkContent()) {
            pointScore += savePointHistory(PointEventType.REVIEW_CONTENT, user);
        }

        if (!modifyReview.checkPhotos() && currReview.checkPhotos()) {
            pointScore += savePointHistory(PointEventType.REVIEW_PHOTO_ZERO, user);
        } else if (modifyReview.checkPhotos() && !currReview.checkPhotos()) {
            pointScore += savePointHistory(PointEventType.REVIEW_PHOTO, user);
        }
        return pointScore;
    }

    private long savePointHistory(PointEventType pointEventType, User user) {
        PointHistory pointHistory = PointHistory.builder()
                .point(pointEventType.getPoint())
                .pointEventType(pointEventType)
                .pointType(pointEventType.getPointType())
                .description(pointEventType.getDescription())
                .user(user)
                .build();
        pointHistoryRepository.save(pointHistory);
        return pointEventType.getPoint();
    }
}
