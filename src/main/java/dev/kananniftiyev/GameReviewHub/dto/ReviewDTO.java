package dev.kananniftiyev.GameReviewHub.dto;

public class ReviewDTO {
    private String reviewerName;
    private String reviewRating;
    private String content;
    private String publicationDate;

    public ReviewDTO() {
    }

    public ReviewDTO(String reviewerName, String reviewRating, String content, String publicationDate) {
        this.reviewerName = reviewerName;
        this.reviewRating = reviewRating;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    public String getReviewerName() {
        return this.reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewRating() {
        return this.reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

}
