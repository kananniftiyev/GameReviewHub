package dev.kananniftiyev.GameReviewHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.kananniftiyev.GameReviewHub.entity.Game;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByName(@Param("name") String name);

    @Query("SELECT g FROM Game g WHERE LOWER(g.name) LIKE %:name%")
    Game findByNameContainingIgnoreCase(@Param("name") String name);

}
