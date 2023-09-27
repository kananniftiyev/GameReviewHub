<h1 align="center">Game Review Hub REST API</h1>
<h4 align="center">GameReviewHub is a comprehensive platform that aggregates game reviews, user ratings, and essential game information, providing gamers with a centralized hub for discovering, comparing, and reviewing video games.</h4>

## Technologies Used

Here are the technologies and dependencies used in this project:

- [Spring Boot](https://spring.io/projects/spring-boot): A framework for building Java applications quickly and easily.
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa): Part of the larger Spring Data project, it makes it easy to easily implement JPA-based repositories.
- [Spring Security](https://spring.io/projects/spring-security): Provides security features for your application, including authentication and authorization.
- [Spring Web](https://spring.io/guides/gs/spring-boot/): Building web applications using Spring MVC.
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools): Tools that make application development in Spring Boot even easier.
- [PostgreSQL](https://www.postgresql.org/): A powerful, open-source relational database system.
- [Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing): Provides testing support in Spring Boot applications.
- [Spring REST Docs](https://spring.io/projects/spring-restdocs): Documentation generation for RESTful services using Spring MVC.
- [Spring Security Test](https://spring.io/guides/gs/securing-web/): Utilities for testing Spring Security.
- [ModelMapper](http://modelmapper.org/): An object mapping library that can automatically transform objects from one model to another.
- [Jsoup](https://jsoup.org/): A Java library for working with real-world HTML.
- [JUnit](https://junit.org/junit5/): A popular testing framework for Java.
- [H2 Database](https://www.h2database.com/html/main.html): A lightweight, in-memory database for development and testing.
- [Mockito](https://site.mockito.org/): A mocking framework for unit testing in Java.


## Table of Contents

- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
- [Usage](#usage)
- [Multithreading](#multithreading)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

To get started with this API, follow these steps:

1. **Clone the Repository**: Clone this repository to your local machine.

   ```bash
   git clone https://github.com/kananniftiyev/GameReviewHub-API.git
   ```
2. **Install Dependencies**: Make sure you have Java and Spring Boot installed. You can also use Maven for dependency management.

3. **Configure the Application**: Update the application configuration files (application.properties or application.yml) with your database and other configuration settings.

4. **Build and Run**: Build and run the application.

    ```bash
    mvn spring-boot:run
    ```

The API should now be running locally at http://localhost:8080.

## Endpoints

### Get All Games

- **URL**: `/api/v1/games`
- **Method**: `GET`
- **Description**: Get a list of all games.
- **Response**: List of GameDTO objects.

### Get Game by ID

- **URL**: `/api/v1/games/{gameId}`
- **Method**: `GET`
- **Description**: Get a game by its ID.
- **Response**: GameDTO object or a 404 response if the game is not found.

### Get Game Review by ID

- **URL**: `/api/v1/games/{gameId}/reviews`
- **Method**: `GET`
- **Description**: Get a game review by its ID.
- **Response**: GameReviewDTO object or a 404 response if the review is not found.

### Search Games

- **URL**: `/api/v1/games/search`
- **Method**: `GET`
- **Description**: Search for games based on various criteria such as name, developer, publisher, release date, and general rating.
- **Parameters**:
  - `name` (optional): Game name
  - `developer` (optional): Game developer
  - `publisher` (optional): Game publisher
  - `releaseDate` (optional): Game release date
  - `generalRating` (optional): General rating of the game
- **Response**: List of GameDTO objects that match the search criteria.

## Usage
- You can use this API to access and manage game-related data for your Game Review Hub project. Use the provided endpoints to retrieve information about games and game reviews, as well as perform searches based on specific criteria.

## Multithreading

To optimize the scraping process, we've implemented parallel threading in the `ScrapScheduler` component. This allows for efficient data retrieval from external sources. The `ScrapScheduler` currently runs the following tasks in parallel:

```java
@Scheduled(fixedRate = 24 * 60 * 60 * 1000) // Runs every 24 hours
public void scheduleScraping() {
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    executorService.submit(() -> reviewScraperService.startScraping(100, 5000));
    executorService.submit(() -> reviewScraperService.startScraping(5001, 10000));
    executorService.submit(() -> reviewScraperService.startScraping(10000, 150000));

    executorService.submit(() -> gameScraperService.scrape());

    // Shutdown the executor service
    executorService.shutdown();
}
```

This design ensures the efficient utilization of resources during the scraping process. Parallel threading enables multiple tasks to run concurrently, enhancing the speed and effectiveness of data retrieval from external sources.


## Contributing
- Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please open an issue or create a pull request.

## License
- This project is licensed under the MIT License.

