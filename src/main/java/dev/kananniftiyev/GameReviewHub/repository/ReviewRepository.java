package dev.kananniftiyev.GameReviewHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.kananniftiyev.GameReviewHub.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
