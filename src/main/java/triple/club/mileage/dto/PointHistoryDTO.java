package triple.club.mileage.dto;

import lombok.Builder;
import lombok.Data;
import triple.club.mileage.domain.User;
import triple.club.mileage.domain.enums.PointEventType;
import triple.club.mileage.domain.enums.PointType;

import javax.persistence.*;

@Data
@Builder
public class PointHistoryDTO {
    private Long pointHistoryId;

    private String pointEventType;

    private String pointType;

    private String description;

    private Long point;

    private String userId;
}
