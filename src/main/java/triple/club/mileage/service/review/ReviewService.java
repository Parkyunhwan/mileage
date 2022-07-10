package triple.club.mileage.service.review;

import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.dto.EventRequestDTO;

import java.util.HashMap;
import java.util.Map;

public interface ReviewService {
    void doService(EventRequestDTO eventRequestDTO);

    ActionType getType();
}
