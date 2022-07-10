package triple.club.mileage.domain;

import lombok.*;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;

import javax.persistence.*;

@Entity
@Table(name = "user_tb")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {
    @Id
    @Column(name = "user_id")
    @Getter
    private String id;

    private String name;

    private Long pointScore;

    public Long addPointScore(long pointScore) {
        this.pointScore += pointScore;
        return this.pointScore;
    }
}
