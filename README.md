<h1 align="center">Game Review Hub REST API</h1>
<h4 align="center">GameReviewHub is a comprehensive platform that aggregates game reviews, user ratings, and essential game information, providing gamers with a centralized hub for discovering, comparing, and reviewing video games.</h4>

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

## Technologies Used

The GameReviewHub project is built using the following technologies and libraries:

- **Java 17**: The primary programming language used for building the application.

- **Spring Boot**: A powerful framework for building Java applications, providing a robust foundation for the project.

- **Spring Boot Data JPA**: A part of the Spring Data project, enabling easy interaction with databases using the Java Persistence API (JPA).

- **Spring Boot Security**: Provides security features to protect the application and manage user authentication.

- **Spring Boot Web**: Enables the development of web-based applications and RESTful APIs.

- **Spring Boot DevTools**: Provides development-time features like automatic application restart.

- **PostgreSQL**: A popular open-source relational database used to store and manage data.

- **Spring REST Docs**: Used for documenting RESTful APIs in a human-readable format.

- **ModelMapper**: A Java library that simplifies object mapping between DTOs (Data Transfer Objects) and domain objects.

- **Jsoup**: A Java library for working with HTML, parsing, and web scraping.


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

