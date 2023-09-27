package dev.kananniftiyev.GameReviewHub.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import dev.kananniftiyev.GameReviewHub.entity.Game;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/GameReviewHub",
        "spring.datasource.username=postgres",
        "spring.datasource.password=kanan123",
        "spring.datasource.driver-class-name=org.postgresql.Driver"
})
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void gameRepository_saveAll_ReturnSavedObject() {
        Game game = new Game("TEST", "TEST", "TEST", "TEST", "TEST", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());

        gameRepository.save(game);

        Game savedGame = gameRepository.findByName("TEST");

        Assertions.assertThat(savedGame).isNotNull();
        Assertions.assertThat(savedGame.getId()).isGreaterThan(0);

        gameRepository.delete(game);
    }

    @Test
    public void gameRepository_findById() {
        Game newGame = new Game("TEST", "TEST", "TEST", "TEST", "TEST", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());

        gameRepository.save(newGame);
        Long gameId = newGame.getId();

        Optional<Game> findGame = gameRepository.findById(gameId);

        Assertions.assertThat(findGame).isNotNull();
    }

    @Test
    public void gameRepository_findByName() {
        Game newGame = new Game("TEST", "TEST", "TEST", "TEST", "TEST", new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());

        gameRepository.save(newGame);

        Game findGame = gameRepository.findByName("TEST");

        Assertions.assertThat(findGame).isNotNull();
    }
}
