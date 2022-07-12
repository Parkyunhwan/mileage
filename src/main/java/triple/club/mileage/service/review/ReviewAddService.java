package triple.club.mileage.service.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import triple.club.mileage.domain.Place;
import triple.club.mileage.domain.Review;
import triple.club.mileage.domain.User;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.dto.EventRequestDTO;
import triple.club.mileage.exception.RestApiException;
import triple.club.mileage.exception.errorcode.UserErrorCode;
import triple.club.mileage.repository.PlaceRepository;
import triple.club.mileage.repository.ReviewRepository;
import triple.club.mileage.repository.UserRepository;
import triple.club.mileage.service.point.PointHistoryService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReviewAddService implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final PointHistoryService pointHistoryService;
    private final UserRepository userRepository;


    /**
     * ActionType : ADD, EventType : Event
     * @param eventRequestDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doService(EventRequestDTO eventRequestDTO) {
        log.info("리뷰 저장");
        String reviewId = eventRequestDTO.getReviewId();
        String content = eventRequestDTO.getContent();
        List<String> attachedPhotoIds = eventRequestDTO.getAttachedPhotoIds();
        String placeId = eventRequestDTO.getPlaceId();
        String userId = eventRequestDTO.getUserId();

        // 리뷰 저장 로직에 필요한 엔티티
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new RestApiException(UserErrorCode.NONEXIST_PLACE));
        User user = userRepository.findById(userId).orElseThrow(() -> new RestApiException(UserErrorCode.NONEXIST_USER));
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        checkReview(place, user, optionalReview);

        // 리뷰생성->포인트이력추가->유저포인트변경->리뷰저장
        Review newReview = Review.createReview(reviewId, content, attachedPhotoIds.size(), user, place);
        long pointScore = pointHistoryService.reviewAdd(newReview, place, user);
        user.addPointScore(pointScore); // 변경감지로 업데이트
        reviewRepository.save(newReview);
    }

    private void checkReview(Place place, User user, Optional<Review> optionalReview) {
        if (optionalReview.isPresent()) {
            throw new RestApiException(UserErrorCode.ALREADY_REGISTER_REVIEW);
        }
        if (reviewRepository.findByPlaceAndUser(place, user).isPresent()) {
            throw new RestApiException(UserErrorCode.ONE_USER_ONE_REVIEW);
        }
    }

    @Override
    public ActionType getType() {
        return ActionType.ADD;
    }
}
