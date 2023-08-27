package dev.kananniftiyev.GameReviewHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.kananniftiyev.GameReviewHub.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
