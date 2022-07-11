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
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ReviewModService implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PointHistoryService pointHistoryService;


    /**
     * ActionType : MOD, EventType : Event
     * @param eventRequestDTO
     */
    @Override
    public void doService(EventRequestDTO eventRequestDTO) {
        log.info("리뷰 수정");
        String reviewId = eventRequestDTO.getReviewId();
        String content = eventRequestDTO.getContent();
        List<String> attachedPhotoIds = eventRequestDTO.getAttachedPhotoIds();
        String placeId = eventRequestDTO.getPlaceId();
        String userId = eventRequestDTO.getUserId();

        // fetch join을 통해 리뷰와 장소, 유저를 한번에 조회
        Review currReview = reviewRepository.findByIdWithFetch(reviewId).orElseThrow(() -> new IllegalStateException("reviewId에 해당하는 review가 존재하지 않습니다."));
        Place place = currReview.getPlace();
        User user = currReview.getUser();

        checkReview(placeId, userId, place, user);

        // 변경리뷰생성->포인트이력추가->리뷰변경->유저포인트변경
        Review modifyReview = Review.createReview(reviewId, content, attachedPhotoIds.size(), user, place);
        long pointScore = pointHistoryService.reviewMod(currReview, modifyReview, place, user);
        currReview.changeReview(content, attachedPhotoIds.size()); // 변경감지로 업데이트
        user.addPointScore(pointScore);
    }

    private void checkReview(String placeId, String userId, Place place, User user) {
        if (place == null || !place.getId().equals(placeId))
            throw new IllegalStateException("해당 장소의 리뷰가 아닙니다.");

        if (user == null || !user.getId().equals(userId))
            throw new IllegalStateException("해당 user의 리뷰가 아닙니다.");
    }

    @Override
    public ActionType getType() {
        return ActionType.MOD;
    }
}