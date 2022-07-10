package triple.club.mileage.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import triple.club.mileage.domain.enums.PointEventType;
import triple.club.mileage.domain.enums.PointType;

import javax.persistence.*;

@Entity
@Table(name = "point_history_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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
}
