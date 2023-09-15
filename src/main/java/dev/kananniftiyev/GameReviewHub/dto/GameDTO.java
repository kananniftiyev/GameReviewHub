package dev.kananniftiyev.GameReviewHub.dto;

import java.util.List;

public class GameDTO {
    private Long id;
    private String name;
    private String developer;
    private String publisher;
    private String releaseDate;
    private String description;
    private List<String> buyLinks;
    private List<String> platforms;
    private List<String> genres;

    public GameDTO() {
    }

    public GameDTO(Long id, String name, String developer, String publisher, String releaseDate, String description,
            List<String> buyLinks, List<String> platforms, List<String> genres) {
        this.id = id;
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.description = description;
        this.buyLinks = buyLinks;
        this.platforms = platforms;
        this.genres = genres;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getBuyLinks() {
        return this.buyLinks;
    }

    public void setBuyLinks(List<String> buyLinks) {
        this.buyLinks = buyLinks;
    }

    public List<String> getPlatforms() {
        return this.platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

}
