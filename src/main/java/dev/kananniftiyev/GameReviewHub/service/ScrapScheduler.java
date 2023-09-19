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

    // TODO: Make them run in parallel
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000) // Run every 24 hours
    public void scheduleScraping() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        /*
         * executorService.submit(() -> gameScraperService.scrape());
         * executorService.submit(() -> reviewScraperService.startScraping(0, 1000));
         * executorService.submit(() -> reviewScraperService.startScraping(1001,2000));
         * executorService.submit(() -> reviewScraperService.startScraping(2001,3000));
         */
        executorService.submit(() -> reviewScraperService.startScraping(1722, 1723));

        // Shut down the executor service
        executorService.shutdown();
    }
}
