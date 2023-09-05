package dev.kananniftiyev.GameReviewHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GameReviewHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameReviewHubApplication.class, args);
	}

}
