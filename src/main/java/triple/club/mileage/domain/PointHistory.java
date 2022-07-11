package triple.club.mileage.domain;

import lombok.*;
import triple.club.mileage.domain.enums.PointEventType;
import triple.club.mileage.domain.enums.PointType;
import triple.club.mileage.dto.PointHistoryDTO;

import javax.persistence.*;

@Entity
@Table(name = "point_history_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PointHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PointEventType pointEventType;

    @Enumerated(EnumType.STRING)
    private PointType pointType;

    private String description;

    private Long point;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PointHistoryDTO convertToPointHistoryDTO() {
        return PointHistoryDTO.builder().pointHistoryId(this.id).point(this.point).pointType(this.pointType.name())
                .pointEventType(this.pointEventType.name()).description(this.description)
                .build();
    }
}
