package dev.kananniftiyev.GameReviewHub.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import dev.kananniftiyev.GameReviewHub.QueryParams.GameSpecification;
import dev.kananniftiyev.GameReviewHub.dto.GameDTO;
import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.entity.Genre;
import dev.kananniftiyev.GameReviewHub.repository.GameRepository;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    public GameService(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;

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

    public List<GameDTO> findGamesBySpec(String name, String developer, String publisher, String releaseDate,
            String generalRating) {
        Specification<Game> spec = GameSpecification.searchByCriteria(name, developer, publisher, releaseDate,
                generalRating);
        return gameRepository.findAll(spec, JpaSort.unsorted())
                .stream()
                .map(game -> modelMapper.map(game, GameDTO.class))
                .collect(Collectors.toList());

    }

}
