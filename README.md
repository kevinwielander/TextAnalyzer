# Text Analyzer Application

## Frontend

This application is a full-stack text analysis tool that allows users to analyze text for vowels and consonants. It consists of a frontend built with Angular and a backend powered by Spring Boot.

### Features

- Analyze text for vowels or consonants.
- Switch between online and offline analysis modes.
- View results in a responsive table.
- REST API for text analysis.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

### Running Unit Tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Backend

The backend is built using Spring Boot and provides RESTful endpoints for analyzing text.

### Running the Application

To run the application, use the following command:

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

### Running Tests

To run the tests, use the following command:

```bash
./mvnw test
```
This will execute all unit and integration tests.

### API Endpoints

- **POST /api/v1/analyze**: Analyzes the provided text for vowels or consonants. Expects a JSON payload with `type` (either 'vowels' or 'consonants') and `text`.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
