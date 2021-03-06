package triple.club.mileage.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "review_tb", indexes = @Index(name = "REVIEW_PLACE_ID_IDX", columnList = "place_id"))
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
