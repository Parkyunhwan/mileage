package triple.club.mileage.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PointEventType {
    // 리뷰 등록 시
    REVIEW_CONTENT("리뷰 1자 이상 텍스트 작성", 1L, PointType.SAVE),
    REVIEW_PHOTO("리뷰 1장 이상 사진 첨부", 1L, PointType.SAVE),
    PLACE_FIRST_REVIEW("특정 장소에 첫 리뷰 작성", 1L, PointType.SAVE),

    // 리뷰 수정 시
    REVIEW_CONTENT_ZERO("리뷰 텍스트 삭제", -1L, PointType.USE),
    REVIEW_PHOTO_ZERO("리뷰 사진 삭제", -1L, PointType.USE),

    // 리뷰 삭제 시
    PLACE_FIRST_REVIEW_DELETE("특정 장소 첫 리뷰 삭제", -1L, PointType.USE);

    private final String description;
    private final Long point;
    private final PointType pointType;
}
