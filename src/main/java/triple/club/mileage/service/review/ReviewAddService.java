package triple.club.mileage.service.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import triple.club.mileage.domain.Place;
import triple.club.mileage.domain.PointHistory;
import triple.club.mileage.domain.Review;
import triple.club.mileage.domain.User;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.PointEventType;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.repository.PlaceRepository;
import triple.club.mileage.repository.PointHistoryRepository;
import triple.club.mileage.repository.ReviewRepository;
import triple.club.mileage.repository.UserRepository;
import triple.club.mileage.service.point.PointHistoryService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewAddService implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final PlaceRepository placeRepository;
    private final PointHistoryService pointHistoryService;
    private final UserRepository userRepository;


    /**
     * ActionType : ADD, EventType : Event
     * @param eventRequestDTO
     */
    @Override
    public void doService(EventRequestDTO eventRequestDTO) {
        log.info("리뷰 저장");
        String reviewId = eventRequestDTO.getReviewId();
        String content = eventRequestDTO.getContent();
        List<String> attachedPhotoIds = eventRequestDTO.getAttachedPhotoIds();
        String placeId = eventRequestDTO.getPlaceId();
        String userId = eventRequestDTO.getUserId();

        Place place = placeRepository.findById(placeId).orElseThrow(() -> new IllegalStateException("no exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("no exist"));

        Review review = Review.createReview(reviewId, content, attachedPhotoIds.size()
                , user, place);
        reviewRepository.save(review);

        log.info("포인트 적립");
        if (review.checkContent()) {
            pointHistoryService.savePointHistory(PointEventType.REVIEW_CONTENT);
        }
        if (review.checkPhotos()) {
            pointHistoryService.savePointHistory(PointEventType.REVIEW_PHOTO);
        }
        if (place.isZeroReview()) {
            pointHistoryService.savePointHistory(PointEventType.PLACE_FIRST_REVIEW);
            place.changeReviewState(false);
        }
    }

    @Override
    public ActionType getType() {
        return ActionType.ADD;
    }
}
