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
import triple.club.mileage.exception.RestApiException;
import triple.club.mileage.exception.errorcode.UserErrorCode;
import triple.club.mileage.repository.ReviewRepository;
import triple.club.mileage.service.point.PointHistoryService;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReviewDeleteService implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PointHistoryService pointHistoryService;

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

        // fetch join을 통해 리뷰와 장소, 유저를 한번에 조회
        Review review = reviewRepository.findByIdWithFetch(reviewId).orElseThrow(() -> new RestApiException(UserErrorCode.NONEXIST_REVIEW));
        Place place = review.getPlace();
        User user = review.getUser();

        checkReview(placeId, userId, place, user);

        // 포인트이력추가->리뷰삭제->유저포인트변경
        long pointScore = pointHistoryService.reviewDelete(review, place, user);
        reviewRepository.deleteById(reviewId);
        user.addPointScore(pointScore); // 변경감지로 업데이트
    }

    private void checkReview(String placeId, String userId, Place place, User user) {
        if (place == null || !place.getId().equals(placeId))
            throw new RestApiException(UserErrorCode.NOT_PLACE_REVIEW);

        if (user == null || !user.getId().equals(userId))
            throw new RestApiException(UserErrorCode.NOT_USER_REVIEW);
    }

    @Override
    public ActionType getType() {
        return ActionType.DELETE;
    }
}
