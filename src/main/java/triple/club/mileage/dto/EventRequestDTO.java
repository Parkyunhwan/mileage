package triple.club.mileage.dto;

import lombok.Getter;
import lombok.Setter;
import triple.club.mileage.domain.enums.ActionType;

import java.util.List;

@Getter
@Setter
public class EventRequestDTO {
    private String type;
    private String action;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;
}
