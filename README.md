<h1 align="center">Game Review Hub REST API</h1>
<h4 align="center">GameReviewHub is a comprehensive platform that aggregates game reviews, user ratings, and essential game information, providing gamers with a centralized hub for discovering, comparing, and reviewing video games.</h4>

## Table of Contents

- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
- [Usage](#usage)
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

## Contributing
- Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please open an issue or create a pull request.

## License
- This project is licensed under the MIT License.

