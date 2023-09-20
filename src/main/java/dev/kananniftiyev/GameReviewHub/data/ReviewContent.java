package dev.kananniftiyev.GameReviewHub.data;

public class ReviewContent {
    private String reviewerName;
    private String reviewRating;
    private String reviewDate;
    private String content;

    public ReviewContent(String reviewerName, String reviewRating, String reviewDate, String content) {
        this.reviewerName = reviewerName;
        this.reviewRating = reviewRating;
        this.reviewDate = reviewDate;
        this.content = content;
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

    public String getReviewDate() {
        return this.reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
