package dev.kananniftiyev.GameReviewHub.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kananniftiyev.GameReviewHub.dto.GameReviewDTO;
import dev.kananniftiyev.GameReviewHub.dto.ReviewDTO;
import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.entity.Review;
import dev.kananniftiyev.GameReviewHub.repository.GameRepository;
import dev.kananniftiyev.GameReviewHub.repository.ReviewRepository;

@Service
public class GameReviewService {
    private final GameRepository gameRepository;
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GameReviewService(GameRepository gameRepository, ReviewRepository reviewRepository,
            ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    public GameReviewDTO findGameReviewById(Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            return null;
        }
        GameReviewDTO gameReviewDTO = modelMapper.map(game, GameReviewDTO.class);

        List<Review> reviews = reviewRepository.findReviewsByGameId(id);
        List<ReviewDTO> reviewDTOs = reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());

        gameReviewDTO.setReviews(reviewDTOs);
        return gameReviewDTO;

    }
}
