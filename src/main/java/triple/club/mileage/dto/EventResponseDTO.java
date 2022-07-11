package triple.club.mileage.dto;

import lombok.Builder;
import lombok.Data;
import triple.club.mileage.domain.enums.ActionType;


@Data
@Builder
public class EventResponseDTO {
    private Long eventId;
}
