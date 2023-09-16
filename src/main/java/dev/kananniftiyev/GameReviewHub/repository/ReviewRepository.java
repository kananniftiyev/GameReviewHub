package dev.kananniftiyev.GameReviewHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import dev.kananniftiyev.GameReviewHub.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Review findbyContent(@Param("content") String content);

    List<Review> findReviewsByGameId(@Param("game_id") Long game_id);

    Review findReviewByContent(@Param("content") String content);
}
