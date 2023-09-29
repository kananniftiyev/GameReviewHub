package dev.kananniftiyev.GameReviewHub.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScrapScheduler {
    private final ReviewScraperService reviewScraperService;
    private final GameScraperService gameScraperService;

    public ScrapScheduler(ReviewScraperService reviewScraperService, GameScraperService gameScraperService) {
        this.reviewScraperService = reviewScraperService;
        this.gameScraperService = gameScraperService;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000) // Run every 24 hours
    public void scheduleScraping() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // executorService.submit(() -> reviewScraperService.startScraping(100, 5000));
        // executorService.submit(() -> reviewScraperService.startScraping(5001,
        // 10000));
        // executorService.submit(() -> reviewScraperService.startScraping(10000,
        // 150000));

        // executorService.submit(() -> gameScraperService.scrape());

        // Shut down the executor service
        executorService.shutdown();
    }
}
