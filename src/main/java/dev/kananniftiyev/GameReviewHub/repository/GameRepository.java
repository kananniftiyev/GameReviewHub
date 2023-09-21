package dev.kananniftiyev.GameReviewHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import dev.kananniftiyev.GameReviewHub.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {

        Game findByName(@Param("name") String name);

}
