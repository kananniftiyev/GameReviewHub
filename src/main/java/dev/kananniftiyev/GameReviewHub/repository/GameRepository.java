package dev.kananniftiyev.GameReviewHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import dev.kananniftiyev.GameReviewHub.entity.Game;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByName(@Param("name") String name);
}
