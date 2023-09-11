/**
 * Represents a video game entity with its properties such as name, developer, publisher, release date, buy links, platforms, and genres.
 * This class is mapped to the "games" table in the database using Jakarta Persistence API annotations.
 */
package dev.kananniftiyev.GameReviewHub.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "developer")
    private String developer;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "description", length = 10000, columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "buy_links", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "buy_links")
    private List<String> buyLinks;

    @ElementCollection
    @CollectionTable(name = "platforms", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "platform")
    private List<String> platforms;

    // TODO: Fix Unefficient Genre Mapping
    @ElementCollection
    @CollectionTable(name = "genres", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "genre")
    private List<String> genres;

    public Game() {
    }

    public Game(Long id, String name, String developer, String publisher, String releaseDate, String description,
            List<String> buyLinks,
            List<String> platforms, List<String> genres) {
        this.id = id;
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.buyLinks = buyLinks;
        this.platforms = platforms;
        this.genres = genres;
        this.description = description;
    }

    public Game(String name, String developer, String publisher, String releaseDate, String description,
            List<String> buyLinks,
            List<String> platforms, List<String> genres) {
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.buyLinks = buyLinks;
        this.platforms = platforms;
        this.genres = genres;
        this.description = description;
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

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", developer='" + getDeveloper() + "'" +
                ", publisher='" + getPublisher() + "'" +
                ", releaseDate='" + getReleaseDate() + "'" +
                ", buyLinks='" + getBuyLinks() + "'" +
                ", platforms='" + getPlatforms() + "'" +
                ", genres='" + getGenres() + "'" +
                "}";
    }

}
