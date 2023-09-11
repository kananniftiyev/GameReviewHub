package dev.kananniftiyev.GameReviewHub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kananniftiyev.GameReviewHub.service.GameReviewService;
import dev.kananniftiyev.GameReviewHub.service.GameService;
import dev.kananniftiyev.GameReviewHub.dto.GameReviewDTO;
import dev.kananniftiyev.GameReviewHub.entity.Game;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {
    private final GameService gameService;
    private final GameReviewService gameReviewService;

    @Autowired
    public GameController(GameService gameService, GameReviewService gameReviewService) {
        this.gameService = gameService;
        this.gameReviewService = gameReviewService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.findAllGames());
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable Long gameId) {
        Game game = gameService.findGameById(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(game);
        }
    }

    @GetMapping("/{gameId}/reviews")
    public ResponseEntity<GameReviewDTO> getGameReviewById(@PathVariable Long gameId) {
        GameReviewDTO gameReviewDTO = gameReviewService.findGameReviewById(gameId);
        if (gameReviewDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(gameReviewDTO);
        }
    }

    // TODO: Query Search

    // TODO: Add endpoint /{gameId}/reviews to get all reviews of the game

}
