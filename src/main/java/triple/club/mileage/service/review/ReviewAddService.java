package triple.club.mileage.service.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public void doService(EventRequestDTO eventRequestDTO) {
        log.info("리뷰 저장");
        String reviewId = eventRequestDTO.getReviewId();
        String content = eventRequestDTO.getContent();
        List<String> attachedPhotoIds = eventRequestDTO.getAttachedPhotoIds();
        String placeId = eventRequestDTO.getPlaceId();
        String userId = eventRequestDTO.getUserId();

        Place place = placeRepository.findById(placeId).orElseThrow(() -> new IllegalStateException("no exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("no exist"));

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent())
            throw new IllegalStateException("이미 등록된 reviewId 입니다.");
        if (reviewRepository.findByPlaceAndUser(place, user).isEmpty())
            log.info("장소에 등록된 리뷰가 없습니다.");
        else {
            log.info("하나의 유저가 한 장소에 2개이상의 리뷰를 작성할 수 없습니다.");
            throw new IllegalStateException("하나의 유저가 한 장소에 2개이상의 리뷰를 작성할 수 없습니다.");
        }

        Review review = Review.createReview(reviewId, content, attachedPhotoIds.size()
                , user, place);


        log.info("포인트 적립");
        Long pointScore = 0L;
        if (review.checkContent()) {
            pointHistoryService.savePointHistory(PointEventType.REVIEW_CONTENT, user);
            pointScore += PointEventType.REVIEW_CONTENT.getPoint();
        }
        if (review.checkPhotos()) {
            pointHistoryService.savePointHistory(PointEventType.REVIEW_PHOTO, user);
            pointScore += PointEventType.REVIEW_PHOTO.getPoint();
        }
        if (place.isZeroReview()) {
            pointHistoryService.savePointHistory(PointEventType.PLACE_FIRST_REVIEW, user);
            pointScore += PointEventType.PLACE_FIRST_REVIEW.getPoint();
            review.changeFirstReviewStatus(true);
            place.changeReviewState(false);
        }
        user.addPointScore(pointScore); // 변경감지로 업데이트
        reviewRepository.save(review);
    }

    @Override
    public ActionType getType() {
        return ActionType.ADD;
    }
}
