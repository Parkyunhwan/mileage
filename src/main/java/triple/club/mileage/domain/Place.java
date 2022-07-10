package triple.club.mileage.domain;

import lombok.*;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "place_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Place extends BaseEntity {
    @Id
    @Column(name = "place_id")
    private String id;

    private String name;

    private boolean zeroReview;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    public void changeReviewState(boolean bool) {
        zeroReview = bool;
    }
}
