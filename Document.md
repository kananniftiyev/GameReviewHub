# Video Game Review Aggregator

## Overview

The Video Game Review Aggregator is a web application that aggregates game reviews, user ratings, and other essential information about video games from various gaming websites. It provides users with a centralized platform to discover, compare, and review video games.

## Endpoints

### 1. Game Information Endpoint

- **URL**: `/api/games/{game_id}`
- **Method**: GET
- **Description**: Retrieve detailed information about a specific game, including its name, developer, publisher, release date, links to buy the game, reviews, user reviews, platforms, and genres.

### 2. Game Reviews Endpoint

- **URL**: `/api/games/{game_id}/reviews`
- **Method**: GET
- **Description**: Get a list of reviews for a specific game, including the review content, reviewer's name, and publication date.

### 3. Game Search Endpoint

- **URL**: `/api/games/search`
- **Method**: GET
- **Description**: Search for games by name, genre, platform, or other criteria.

## Basic Security

To ensure the security and integrity of the application, implement the following security measures:

- **Authentication**: Implement user authentication to restrict access to certain features.
- **Authorization**: Control user access to endpoints based on roles and permissions.
- **Input Validation**: Sanitize and validate user inputs to prevent SQL injection and other vulnerabilities.
- **Rate Limiting**: Implement rate limiting to prevent abuse of the API.
- **HTTPS**: Use HTTPS to encrypt data transmitted between clients and the server.

## Sites to Scrape

To populate the database with game-related data, scrape information from the following gaming websites:

1. [Metacritic](https://www.metacritic.com/)
2. [OpenCritic](https://opencritic.com/)
3. [GameSpot](https://www.gamespot.com/)
4. [Steam](https://store.steampowered.com/)
5. [GOG](https://www.gog.com/)

## Database

Use a relational database to store the scraped data and user-generated content. Here's a basic schema for the database:

### Game Table

- `game_id` (Primary Key)
- `name` 
- `developer`
- `publisher`
- `release_date`
- `description`
- `buy_links` (JSON array of URLs)
- `platforms` (JSON array of platforms)
- `genres` (JSON array of genres)

### Review Table

- `review_id` (Primary Key)
- `game_id` (Foreign Key)
- `content`
- `reviewer_name`
- `publication_date`

## Data Scrapping Process

1. Scraping Game Data:

   - Fetch game information from the selected websites using web scraping techniques.
   - Extract game name, developer, publisher, release date, buy links, platforms, and genres.

2. Scraping Reviews:

   - Crawl reviews for each game from the selected websites.
   - Extract review content, reviewer's name, and publication date.

3. Store Data:
   - Store the scraped data in the database tables defined above.
