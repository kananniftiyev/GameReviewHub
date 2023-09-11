package dev.kananniftiyev.GameReviewHub.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.repository.GameRepository;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game findGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public Game findGameByName(String name) {
        return gameRepository.findByName(name);
    }

    // TODO: Query methods

}
