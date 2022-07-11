package triple.club.mileage.domain;

import lombok.*;
import triple.club.mileage.domain.enums.ActionType;
import triple.club.mileage.domain.enums.EventType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "review_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    private String id;

    private String content;

    private int photo_size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public static Review createReview(String reviewId, String content, int photos_size, User user, Place place) {
        return Review.builder().id(reviewId)
                .content(content)
                .photo_size(photos_size)
                .user(user)
                .place(place)
                .build();
    }

    public boolean checkContent() {
        return this.content.length() > 0;
    }

    public boolean checkPhotos() {
        return this.photo_size > 0;
    }

    public void changeReview(String content, int photo_size) {
        this.content = content;
        this.photo_size = photo_size;
    }
}
