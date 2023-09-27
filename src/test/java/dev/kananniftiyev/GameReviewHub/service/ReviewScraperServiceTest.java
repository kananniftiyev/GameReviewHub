package dev.kananniftiyev.GameReviewHub.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import dev.kananniftiyev.GameReviewHub.data.ReviewContent;
import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.entity.Review;
import dev.kananniftiyev.GameReviewHub.repository.GameRepository;
import dev.kananniftiyev.GameReviewHub.repository.ReviewRepository;

@SpringBootTest
public class ReviewScraperServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private ReviewScraperService reviewScraperService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testScrapName() throws Exception {
        // Create a mock Document object
        Document mockDoc = Jsoup.parse("<h1 class='mb-0'>Test Game Name</h1>");

        // Call the scrapName method and verify the result
        String name = reviewScraperService.scrapName(mockDoc);

        // Assert that the method correctly extracts the game name
        assertEquals("Test Game Name", name);
    }

    @Test
    public void testScrapPlatforms() throws Exception {
        // Create a mock Document object
        Document mockDoc = Jsoup.parse(
                "<div class='platforms'><span><strong>Platform 1</strong></span><span><strong>Platform 2</strong></span></div>");

        // Call the scrapPlatforms method and verify the result
        List<String> platforms = reviewScraperService.scrapPlatforms(mockDoc);

        // Assert that the method correctly extracts the platform names
        assertEquals(2, platforms.size());
        assertEquals("Platform 1", platforms.get(0));
        assertEquals("Platform 2", platforms.get(1));
    }

    @Test
    public void testScrapGeneralGameRating() throws Exception {
        // Create a mock Document object
        Document mockDoc = Jsoup.parse("<div class='inner-orb'>9.5</div>");

        // Call the scrapGeneralGameRating method and verify the result
        String rating = reviewScraperService.scrapGeneralGameRating(mockDoc);

        // Assert that the method correctly extracts the general game rating
        assertEquals("9.5", rating);
    }

    @Test
    public void testScrapReviewContents() throws Exception {
        // Create a mock Document object with review content
        Document mockDoc = Jsoup.parse("<app-review-row>" +
                "<div class='row review-row'>" +
                "<app-score-display-raw><span>8.0</span></app-score-display-raw>" +
                "<span class='outlet-name'><a>Reviewer 1</a></span>" +
                "<p class='mb-0 wspw'>Review Content 1</p>" +
                "<div class='text-right date-block'>Review Date 1</div>" +
                "</div></app-review-row>" +
                "<app-review-row>" +
                "<div class='row review-row'>" +
                "<app-score-display-raw><span>7.5</span></app-score-display-raw>" +
                "<span class='outlet-name'><a>Reviewer 2</a></span>" +
                "<p class='mb-0 wspw'>Review Content 2</p>" +
                "<div class='text-right date-block'>Review Date 2</div>" +
                "</div></app-review-row>");

        // Call the scrapReviewContents method and verify the result
        List<ReviewContent> reviewContents = reviewScraperService.scrapReviewContents(mockDoc, 1);

        // Assert that the method correctly extracts review content
        assertEquals(2, reviewContents.size());
        assertEquals("8.0", reviewContents.get(0).getReviewRating());
        assertEquals("Reviewer 1", reviewContents.get(0).getReviewerName());
        assertEquals("Review Content 1", reviewContents.get(0).getContent());
        assertEquals("Review Date 1", reviewContents.get(0).getReviewDate());
        assertEquals("7.5", reviewContents.get(1).getReviewRating());
        assertEquals("Reviewer 2", reviewContents.get(1).getReviewerName());
        assertEquals("Review Content 2", reviewContents.get(1).getContent());
        assertEquals("Review Date 2", reviewContents.get(1).getReviewDate());
    }

    // Add more test cases for other methods and scenarios as needed

}
