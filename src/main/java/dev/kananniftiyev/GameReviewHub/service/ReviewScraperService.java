package dev.kananniftiyev.GameReviewHub.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.entity.Review;
import dev.kananniftiyev.GameReviewHub.repository.GameRepository;
import dev.kananniftiyev.GameReviewHub.repository.ReviewRepository;

@Service
public class ReviewScraperService {
    private final ReviewRepository reviewRepository;
    private final GameRepository gameRepository;

    @Autowired
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

                // TODO: Check if it works good
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
                List<String> reviewRatings = scrapReviewRating(doc);
                List<String> contents = scrapContent(doc);
                List<String> reviewerNames = scrapReviewerName(doc);
                List<String> reviewDates = scrapReviewDate(doc);

                for (int j = 0; j < reviewRatings.size(); j++) {
                    String reviewRating = reviewRatings.get(j);
                    String content = contents.get(j);
                    String reviewerName = reviewerNames.get(j);
                    String reviewDate = reviewDates.get(j);

                    if (reviewRepository.findReviewByContent(content) != null) {
                        continue;
                    }
                    Review review = new Review(game, reviewRating, content, reviewerName, reviewDate);

                    reviewRepository.save(review);
                }
                // TODO: Check for next page of reviews

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

    private List<String> scrapReviewRating(Document doc) {
        Elements ratingElements = doc.select("span.score-number-bold");
        List<String> ratings = new ArrayList<>();

        if (ratingElements != null) {
            for (Element ratingElement : ratingElements) {
                ratings.add(ratingElement.text());
            }
        }

        return ratings;
    }

    private List<String> scrapContent(Document doc) {
        Elements contentElements = doc.select("p.mb-0.wspw");
        List<String> contents = new ArrayList<>();

        if (contentElements != null) {
            for (Element contentElement : contentElements) {
                contents.add(contentElement.text());
            }
        }

        return contents;
    }

    private List<String> scrapReviewerName(Document doc) {
        Elements reviewerNameElements = doc.select("span.outlet-name > a.deco-none");
        List<String> reviewerNames = new ArrayList<>();

        if (reviewerNameElements != null) {
            for (Element reviewerNameElement : reviewerNameElements) {
                System.out.println(reviewerNameElement.text());
                reviewerNames.add(reviewerNameElement.text());
            }
        }

        return reviewerNames;
    }

    private List<String> scrapReviewDate(Document doc) {
        Elements reviewDateElements = doc.select("div.text-right.date-block");
        List<String> reviewDates = new ArrayList<>();

        if (reviewDateElements != null) {
            for (Element reviewDateElement : reviewDateElements) {
                reviewDates.add(reviewDateElement.text());
            }
        }

        return reviewDates;
    }

}
