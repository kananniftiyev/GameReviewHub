package dev.kananniftiyev.GameReviewHub.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScrapScheduler {
    private final ReviewScraperService reviewScraperService;
    private final GameScraperService gameScraperService;

    @Autowired
    public ScrapScheduler(ReviewScraperService reviewScraperService, GameScraperService gameScraperService) {
        this.reviewScraperService = reviewScraperService;
        this.gameScraperService = gameScraperService;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000) // Run every 24 hours
    public void scheduleScraping() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // executorService.submit(() -> gameScraperService.scrape());
        // executorService.submit(() -> reviewScraperService.startScraping(14856,
        // 14858));
        // executorService.submit(() -> reviewScraperService.startScraping(1001,2000));
        // executorService.submit(() -> reviewScraperService.startScraping(2001,3000));

        // Shut down the executor service
        executorService.shutdown();
    }
}
