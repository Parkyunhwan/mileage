package triple.club.mileage.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.validation.EnumValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class EventRequestDTO {
    @EnumValidator(enumType = EventType.class, message = "유효하지 않은 이벤트 타입입니다.")
    private String type;

    @EnumValidator(enumType = ActionType.class, message = "유효하지 않은 Action 타입입니다.")
    private String action;

    @NotBlank(message = "reviewId는 필수값입니다.")
    private String reviewId;
    @NotNull
    private String content;

    private List<String> attachedPhotoIds;

    @NotBlank(message = "userId는 필수값입니다.")
    private String userId;
    @NotBlank(message = "placeId는 필수값입니다.")
    private String placeId;
}
