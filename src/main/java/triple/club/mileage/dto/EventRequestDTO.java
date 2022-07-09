package triple.club.mileage.dto;

import lombok.Getter;
import lombok.Setter;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.validation.EnumValidator;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class EventRequestDTO {
    @EnumValidator(enumType = EventType.class, message = "유효하지 않은 이벤트 타입입니다.")
    private String type;

    @EnumValidator(enumType = ActionType.class, message = "유효하지 않은 Action 타입입니다.")
    private String action;

    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;

    @NotBlank(message = "비워둘 수 없습니다.")
    private String userId;

    private String placeId;
}
