package triple.club.mileage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import triple.club.mileage.domain.Place;
import triple.club.mileage.domain.Review;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {
    Optional<Review> findByPlace(Place place);

    @Query("select r from Review r" +
                    " join fetch r.place p" +
                    " join fetch r.user u" +
                    " where r.id = :reviewId")
    Optional<Review> findByIdWithFetch(String reviewId);
}
