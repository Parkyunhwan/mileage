package triple.club.mileage.service.event;

import triple.club.mileage.domain.enums.EventType;
import triple.club.mileage.dto.EventRequestDTO;

public interface EventAction {
    String action(EventRequestDTO eventRequestDTO);
    EventType getType();
}
