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

    private String firstReviewId;

    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    public void deleteFirstReviewId() {
        firstReviewId = null;
    }

    public boolean hasFirstReviewId() {
        return firstReviewId != null;
    }

    public void setFirstReviewId(String firstReviewId) {
        this.firstReviewId = firstReviewId;
    }

    public boolean hasOneReview(Place place) {
        return place.getReviews().size() == 1;
    }

    public boolean compareFirstReviewId(String reviewId) {
        return hasFirstReviewId() && getFirstReviewId().equals(reviewId);
    }
}
