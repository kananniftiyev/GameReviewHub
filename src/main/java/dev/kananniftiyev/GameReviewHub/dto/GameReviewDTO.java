package dev.kananniftiyev.GameReviewHub.dto;

import java.util.List;

public class GameReviewDTO {
    private long id;
    private String name;
    private List<ReviewDTO> reviews;

    public GameReviewDTO() {
    }

    public GameReviewDTO(long id, String name, List<ReviewDTO> reviews) {
        this.id = id;
        this.name = name;
        this.reviews = reviews;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReviewDTO> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

}
