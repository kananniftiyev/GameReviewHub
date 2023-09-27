package dev.kananniftiyev.GameReviewHub.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.entity.Review;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void reviewRepository_findReviewsByGameId() {
        Game game = new Game("TEST", "TEST", "TEST", "TEST", "TEST", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        gameRepository.save(game);

        Review review = new Review(game, "5", "Great game", "John Doe", "2023-09-27");
        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findReviewsByGameId(game.getId());

        Assertions.assertThat(reviews).isNotEmpty();
    }

    @Test
    public void reviewRepository_findReviewByContent() {
        Review review = new Review(null, "5", "Awesome review content", "Alice", "2023-09-27");
        reviewRepository.save(review);

        Review foundReview = reviewRepository.findReviewByContent("Awesome review content");

        Assertions.assertThat(foundReview).isNotNull();
        Assertions.assertThat(foundReview.getReviewerName()).isEqualTo("Alice");
    }

    @Test
    public void reviewRepository_saveAndFindAllReviews() {
        Game game = new Game("TEST", "TEST", "TEST", "TEST", "TEST", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        gameRepository.save(game);

        Review review1 = new Review(game, "5", "Great game", "John Doe", "2023-09-27");
        Review review2 = new Review(game, "4", "Good game", "Alice", "2023-09-28");

        reviewRepository.saveAll(Arrays.asList(review1, review2));

        List<Review> reviews = reviewRepository.findAll();

        Assertions.assertThat(reviews).hasSize(2);
        Assertions.assertThat(reviews).extracting(Review::getReviewRating).contains("5", "4");
    }

    @Test
    public void reviewRepository_updateReviewContent() {
        Review review = new Review(null, "4", "Initial content", "Bob", "2023-09-27");
        reviewRepository.save(review);

        Long reviewId = review.getId();

        // Update review content
        review.setContent("Updated content");
        reviewRepository.save(review);

        Review updatedReview = reviewRepository.findById(reviewId).orElse(null);

        Assertions.assertThat(updatedReview).isNotNull();
        Assertions.assertThat(updatedReview.getContent()).isEqualTo("Updated content");
    }

    @Test
    public void reviewRepository_deleteReview() {
        Review review = new Review(null, "4", "To be deleted", "Charlie", "2023-09-27");
        reviewRepository.save(review);
        Long reviewId = review.getId();

        reviewRepository.deleteById(reviewId);

        Review deletedReview = reviewRepository.findById(reviewId).orElse(null);

        Assertions.assertThat(deletedReview).isNull();
    }
}
