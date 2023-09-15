package dev.kananniftiyev.GameReviewHub.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kananniftiyev.GameReviewHub.dto.GameDTO;
import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.entity.Genre;
import dev.kananniftiyev.GameReviewHub.repository.GameRepository;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GameService(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;

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

    public List<GameDTO> findAllGamesDTO() {
        return gameRepository.findAll().stream()
                .map(game -> modelMapper.map(game, GameDTO.class))
                .collect(Collectors.toList());
    }

    public GameDTO findGameDTOById(Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        return modelMapper.map(game, GameDTO.class);
    }

    // TODO: Query methods

}
