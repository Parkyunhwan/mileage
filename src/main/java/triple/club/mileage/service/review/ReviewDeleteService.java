package triple.club.mileage.service.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import triple.club.mileage.domain.Place;
import triple.club.mileage.domain.Review;
import triple.club.mileage.domain.User;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.PointEventType;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.repository.PlaceRepository;
import triple.club.mileage.repository.ReviewRepository;
import triple.club.mileage.repository.UserRepository;
import triple.club.mileage.service.point.PointHistoryService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReviewDeleteService implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final PointHistoryService pointHistoryService;
    private final UserRepository userRepository;

    /**
     * ActionType : DELETE, EventType : Event
     * @param eventRequestDTO
     */
    @Override
    public void doService(EventRequestDTO eventRequestDTO) {
        log.info("리뷰 삭제");
        String reviewId = eventRequestDTO.getReviewId();
        String placeId = eventRequestDTO.getPlaceId();
        String userId = eventRequestDTO.getUserId();

        Review review = reviewRepository.findByIdWithFetch(reviewId).orElseThrow(() -> new IllegalStateException("해당 reviewId로 등록된 리뷰가 없습니다."));
        Place place = review.getPlace();
        User user = review.getUser();

        if (place == null || !place.getId().equals(placeId))
            throw new IllegalStateException("해당 장소의 리뷰가 아닙니다.");

        if (user == null || !user.getId().equals(userId))
            throw new IllegalStateException("해당 user의 리뷰가 아닙니다.");

        log.info("포인트 차감");
        Long pointScore = 0L;
        if (review.checkContent()) {
            pointHistoryService.savePointHistory(PointEventType.REVIEW_CONTENT_ZERO, user);
            pointScore += PointEventType.REVIEW_CONTENT_ZERO.getPoint();
        }
        if (review.checkPhotos()) {
            pointHistoryService.savePointHistory(PointEventType.REVIEW_PHOTO_ZERO, user);
            pointScore += PointEventType.REVIEW_PHOTO_ZERO.getPoint();
        }
        if (!place.isZeroReview() && review.isFirstReview()) {
            pointHistoryService.savePointHistory(PointEventType.PLACE_FIRST_REVIEW_DELETE, user);
            pointScore += PointEventType.PLACE_FIRST_REVIEW_DELETE.getPoint();
        }

        if (place.getReviews().size() - 1 == 0)
            place.changeReviewState(true);
        reviewRepository.deleteById(reviewId);
        user.addPointScore(pointScore); // 변경감지로 업데이트
    }

    @Override
    public ActionType getType() {
        return ActionType.DELETE;
    }
}
