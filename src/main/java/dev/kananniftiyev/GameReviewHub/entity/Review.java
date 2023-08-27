package dev.kananniftiyev.GameReviewHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "reviewer_name")
    private String reviewerName;

    @Column(name = "publication_date")
    private Date publicationDate;

    public Review() {
    }

    public Review(Long id, Game game, String content, String reviewerName, Date publicationDate) {
        this.id = id;
        this.game = game;
        this.content = content;
        this.reviewerName = reviewerName;
        this.publicationDate = publicationDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReviewerName() {
        return this.reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", game='" + getGame() + "'" +
                ", content='" + getContent() + "'" +
                ", reviewerName='" + getReviewerName() + "'" +
                ", publicationDate='" + getPublicationDate() + "'" +
                "}";
    }

}
