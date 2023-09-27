package dev.kananniftiyev.GameReviewHub.repository;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.kananniftiyev.GameReviewHub.entity.Genre;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void genreRepository_findByName() {
        Genre genre = new Genre("Action");
        genreRepository.save(genre);

        Genre foundGenre = genreRepository.findByName("Action");

        Assertions.assertThat(foundGenre).isNotNull();
        Assertions.assertThat(foundGenre.getName()).isEqualTo("Action");
    }

    @Test
    public void genreRepository_saveAndFindAllGenres() {
        Genre genre1 = new Genre("Action");
        Genre genre2 = new Genre("Adventure");

        genreRepository.saveAll(Arrays.asList(genre1, genre2));

        List<Genre> genres = genreRepository.findAll();

        Assertions.assertThat(genres).hasSize(2);
        Assertions.assertThat(genres).extracting(Genre::getName).contains("Action", "Adventure");
    }

    @Test
    public void genreRepository_deleteGenre() {
        Genre genre = new Genre("Strategy");
        genreRepository.save(genre);
        Long genreId = genre.getId();

        genreRepository.deleteById(genreId);

        Genre deletedGenre = genreRepository.findById(genreId).orElse(null);

        Assertions.assertThat(deletedGenre).isNull();
    }
}
