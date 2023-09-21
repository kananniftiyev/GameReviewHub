package dev.kananniftiyev.GameReviewHub.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import dev.kananniftiyev.GameReviewHub.data.ReviewContent;
import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.entity.Review;
import dev.kananniftiyev.GameReviewHub.repository.GameRepository;
import dev.kananniftiyev.GameReviewHub.repository.ReviewRepository;

@Service
public class ReviewScraperService {
    private final ReviewRepository reviewRepository;
    private final GameRepository gameRepository;

    public ReviewScraperService(ReviewRepository reviewRepository, GameRepository gameRepository) {
        this.reviewRepository = reviewRepository;
        this.gameRepository = gameRepository;
    }

    public void startScraping(Integer startId, Integer endId) {
        for (int i = startId; i <= endId; i++) {
            String url = "https://opencritic.com/game/" + i + "/*";
            try {
                Document doc = Jsoup.connect(url).get();
                String name = scrapName(doc);

                // TODO: FIX THIS LATER
                Game game = gameRepository.findByName(name);
                if (game == null) {
                    System.out.println("Game not found: " + name);
                    continue;
                } else if (!name.equals(game.getName())) {
                    System.out.println("Game name does not match: " + name + " " + game.getName());

                    continue;
                }
                String generalRating = scrapGeneralGameRating(doc);
                List<String> platforms = scrapPlatforms(doc);
                game.setPlatforms(platforms);
                game.setGeneralRating(generalRating);
                gameRepository.save(game);
                url = "https://opencritic.com/game/" + i + "/*/reviews";
                doc = Jsoup.connect(url).get();
                List<ReviewContent> reviewContents = scrapReviewContents(doc, i);

                for (int j = 0; j < reviewContents.size(); j++) {
                    String reviewRating = reviewContents.get(j).getReviewRating();
                    String content = reviewContents.get(j).getContent();
                    String reviewerName = reviewContents.get(j).getReviewerName();
                    String reviewDate = reviewContents.get(j).getReviewDate();

                    if (reviewRepository.findReviewByContent(content) != null) {
                        continue;
                    }
                    Review review = new Review(game, reviewRating, content, reviewerName, reviewDate);

                    reviewRepository.save(review);
                    Thread.sleep(5000);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

    }

    private String scrapName(Document doc) {
        Element nameElement = doc.selectFirst("h1.mb-0");
        if (nameElement == null) {
            return null;
        }
        return nameElement.text();
    }

    private List<String> scrapPlatforms(Document doc) {
        Elements platformElement = doc.select("div.platforms > span > strong");
        List<String> platforms = new ArrayList<>();
        if (platformElement == null) {
            return null;
        }
        for (Element element : platformElement) {
            platforms.add(element.text());
        }
        return platforms;
    }

    private String scrapGeneralGameRating(Document doc) {
        Element ratingElement = doc.selectFirst("div.inner-orb");
        if (ratingElement == null) {
            return null;
        }
        return ratingElement.text();
    }

    private List<ReviewContent> scrapReviewContents(Document doc, int i) {
        List<ReviewContent> reviewContents = new ArrayList<>();
        Elements elements = doc.select("app-review-row");
        for (int k = 2; k <= 10; k++) {
            String url = "https://opencritic.com/game/" + i + "/*/reviews?page=" + k;
            try {
                doc = Jsoup.connect(url).get();
                Elements extraElements = doc.select("app-review-row");
                if (extraElements.isEmpty()) {
                    break; // Changed This due to if there is no extra app-review-row then there wont be on
                           // other pages neither.
                }
                for (Element extra : extraElements) {
                    elements.add(extra);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        for (Element element : elements) {
            Element divInsideMain = element.selectFirst("div.row.review-row");

            if (divInsideMain != null) {
                Element reviewScoreElement = divInsideMain.selectFirst("app-score-display-raw > span");
                Element reviewerNameElement = divInsideMain.selectFirst("span.outlet-name > a");
                Element reviewContentElement = divInsideMain.selectFirst("p.mb-0.wspw");
                Element reviewDateElement = divInsideMain.selectFirst("div.text-right.date-block");

                // Extract data from elements
                String reviewRating = (reviewScoreElement != null) ? reviewScoreElement.text() : "";
                String reviewerName = (reviewerNameElement != null) ? reviewerNameElement.text() : "";
                String content = (reviewContentElement != null) ? reviewContentElement.text() : "";
                String reviewDate = (reviewDateElement != null) ? reviewDateElement.text() : "";

                ReviewContent reviewContent = new ReviewContent(reviewerName, reviewRating, reviewDate, content);
                reviewContents.add(reviewContent);
            }
        }

        return reviewContents;

    }
}
