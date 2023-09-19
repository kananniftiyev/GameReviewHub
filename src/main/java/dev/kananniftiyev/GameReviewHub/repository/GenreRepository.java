package dev.kananniftiyev.GameReviewHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.kananniftiyev.GameReviewHub.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);

}
