package dev.kananniftiyev.GameReviewHub.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.kananniftiyev.GameReviewHub.entity.Game;
import dev.kananniftiyev.GameReviewHub.repository.GameRepository;
import dev.kananniftiyev.GameReviewHub.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GameScraperService {
    private final GameRepository gameRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public GameScraperService(GameRepository gameRepository, RestTemplate restTemplate) {
        this.gameRepository = gameRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * Scrapes game data from Steam API and saves it to the database.
     * Uses Jsoup to connect to the Steam market URL and scrape game data.
     * Skips games that already exist in the database or contain certain keywords.
     * Sleeps for 5 seconds between requests to avoid getting blocked by the server.
     */
    public void scrape() {
        String url = "https://api.steampowered.com/ISteamApps/GetAppList/v2/";
        Map<String, Object> steamAppList = restTemplate.getForObject(url, Map.class);
        if (steamAppList == null) {
            return;
        }

        Map<String, Object> appList = (Map<String, Object>) steamAppList.get("applist");
        List<Map<String, Object>> apps = (List<Map<String, Object>>) appList.get("apps");

        for (Map<String, Object> app : apps) {
            Integer appId = (Integer) app.get("appid");
            String appName = (String) app.get("name");

            if (appId == null || appName == null || appName == "") {
                continue;
            }
            String marketUrl = "https://store.steampowered.com/app/" + appId;

            try {
                Document doc = Jsoup.connect(marketUrl).get();
                // System.out.println(doc);

                String GameName = scrapeGameName(doc);
                String GameDeveloper = scrapeGameDeveloper(doc);
                String GamePublisher = scrapeGamePublisher(doc);
                String GameReleaseDate = scrapeGameReleaseDate(doc);
                String GameDescription = scrapeGameDescription(doc);
                List<String> GameBuyLinks = new ArrayList<String>();
                List<String> GamePlatforms = new ArrayList<String>();
                List<String> GameGenres = scrapeGameGenres(doc);

                GameBuyLinks.add(marketUrl);
                GamePlatforms.add("PC");

                Game game = gameRepository.findByName(GameName);

                if (game != null) {
                    // Game with the same name already exists, skip saving
                    System.out.println("Skiped");
                    continue;
                }

                if (containsKeyWord(appName, "DLC", "Expansion", "Bonus", "ArtBook", "Soundtrack", "Pack")) {
                    continue; // Skip processing this game
                }

                // Create and save the game
                game = new Game(GameName, GameDeveloper, GamePublisher, GameReleaseDate, GameDescription,
                        GameBuyLinks, GamePlatforms, GameGenres);
                gameRepository.save(game);

                // Thread.sleep(5000); // Sleep for 5 second to avoid getting blocked by the
                // server

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    private String scrapeGameName(Document doc) {
        Element element = doc.getElementById("appHubAppName");
        if (element != null) {
            String gameName = element.text();
            if (gameName != null && !gameName.isEmpty()) {
                return gameName;
            }
        }
        return null; // Return null if the element or its text is not found
    }

    private String scrapeGameDeveloper(Document doc) {
        Element element = doc.getElementById("developers_list");
        if (element != null) {
            Element developer = element.getElementsByTag("a").first();
            if (developer != null) {
                String gameDeveloper = developer.text();
                return gameDeveloper;
            }
        }
        return null; // Return null if the element or developer link is not found
    }

    private String scrapeGamePublisher(Document doc) {
        Elements devRows = doc.getElementsByClass("dev_row");
        if (devRows.size() > 1) {
            Element publisher = devRows.get(1).getElementsByClass("summary column").first();
            if (publisher != null) {
                String gamePublisher = publisher.text();
                return gamePublisher;
            }
        }
        return null; // Return null if the elements or publisher info is not found
    }

    private String scrapeGameReleaseDate(Document doc) {
        Element element = doc.getElementsByClass("release_date").first();
        if (element != null) {
            Element releaseDate = element.getElementsByClass("date").first();
            if (releaseDate != null) {
                String gameReleaseDate = releaseDate.text();
                return gameReleaseDate;
            }
        }
        return null; // Return null if the element or release date is not found
    }

    private String scrapeGameDescription(Document doc) {
        Element element = doc.getElementsByClass("game_description_snippet").first();
        if (element != null) {
            String gameDescription = element.text();
            return gameDescription;
        }
        return null; // Return null if the element or game description is not found
    }

    private List<String> scrapeGameGenres(Document doc) {
        Elements elements = doc.getElementsByClass("glance_tags popular_tags");
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }

        Elements genres = elements.get(0).getElementsByClass("app_tag");
        List<String> gameGenres = new ArrayList<String>();

        for (Element genre : genres) {
            String genreText = genre.text().trim(); // Remove leading and trailing whitespace
            if (!genreText.equals("+")) {
                gameGenres.add(genreText);
            }
        }

        return gameGenres;
    }

    private boolean containsKeyWord(String gameName, String... wordsToFind) {
        for (String wordToFind : wordsToFind) {
            // Create a regular expression pattern with word boundaries
            String regex = "\\b" + Pattern.quote(wordToFind) + "\\b";

            // Create a pattern and matcher
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(gameName);

            // Check if the word is found in the game name
            if (matcher.find()) {
                return true; // At least one word is found
            }
        }
        return false; // None of the words are found
    }

    // TODO: Epic Games Link(Think about it)
    // TODO: GamePlatforms
    // TODO: Review Scraper

}
