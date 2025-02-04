# Selenium-API-Scraper
# **Detailed Implementation Document for Selenium API Scraper**

## **1. Project Overview**

This project is a **Spring Boot-based web service** that performs the following tasks:

1. Calls a **public REST API** to fetch the first state name from India.
2. Uses **Selenium WebDriver** to scrape Google search results for that state.
3. Extracts the **top 2 search results** (title + link).
4. Saves the scraped data into an **Excel file** using Apache POI.
5. Provides a **REST API endpoint** for users to trigger the search operation.
6. Implements **global exception handling** and **logging** for better debugging and error tracking.
7. Includes **JUnit test cases** to validate different components.

## **2. Tech Stack & Dependencies**

### **Backend Technologies**

- **Spring Boot** – Framework for building Java-based web applications.
- **Selenium WebDriver** – A browser automation tool used for web scraping.
- **Apache POI** – A Java library to handle Excel file operations.
- **Apache HTTP Client** – Used to make HTTP requests to external APIs.
- **Jackson Databind** – Handles JSON serialization and deserialization.
- **Java 17** – Required for compatibility with Spring Boot 3.x.
- **SLF4J & Logback** – Used for logging throughout the application.
- **JUnit & Mockito** – Used for unit and integration testing.

## **3. Project Architecture**

The project follows the **Controller-Service-Utility** pattern:

1. **Configuration Layer** – Stores common configurations in `application.properties`.
2. **Controller Layer** – Handles HTTP requests and maps them to appropriate service methods.
3. **Service Layer** – Implements business logic, interacts with Selenium for web scraping.
4. **Utility Layer** – Handles common functionalities like writing data to Excel files.
5. **Global Exception Handler** – Manages application-wide exceptions.
6. **Logging Integration** – Provides structured logging for debugging and monitoring.
7. **JUnit Test Cases** – Ensures correctness of the implemented functionalities.

## **4. Technical Explanation of Components**

### **4.1 Configuration Layer**
- Stores common properties such as Selenium driver path, timeouts, and Excel file path.
- Uses `@ConfigurationProperties` to dynamically load values from `application.properties`.

### **4.2 Controller Layer**
- Defines RESTful endpoints for triggering the scraping process.
- Uses `@RestController` to expose API methods.
- Implements structured logging for tracking API calls.

### **4.3 Service Layer**
- Implements Selenium WebDriver to automate Google search.
- Configures ChromeDriver with headless mode for efficient execution.
- Uses `WebDriverWait` to handle dynamic page elements.
- Extracts search result titles and URLs from Google’s result page.
- Calls `ScraperUtil` to write the extracted data into an Excel file.
- Implements proper error handling to manage issues like `NoSuchElementException`, `TimeoutException`, and connection failures.
- Uses structured logging (`LoggerFactory.getLogger()`) to track execution flow and debugging purposes.
- Ensures efficient memory management by closing WebDriver instances after execution.
- Can be extended to support multiple search engines like Bing or Yahoo in the future.

### **4.4 Utility Layer**
- Utilizes **Apache POI** to create and write Excel files.
- Generates an Excel sheet with headers (`Search Keyword`, `Title`, `Link`).
- Saves the output file locally for easy access.
- Uses `try-with-resources` to manage file operations and prevent resource leaks.
- Implements a method to append data if the file already exists, ensuring new search results do not overwrite previous ones.
- Includes data validation to check for empty or duplicate entries before writing to the Excel file.
- Optimized for performance to handle large-scale data efficiently.

### **4.5 Global Exception Handler**
- Implements `@ControllerAdvice` to catch and handle exceptions globally.
- Returns meaningful error responses with HTTP status codes.
- Logs exception details for debugging.

### **4.6 Logging Mechanism**
- Uses **SLF4J with Logback** for structured logging.
- Logs **method entry, exit, and errors** in each layer.
- Configured in `logback.xml` to support different log levels (INFO, DEBUG, ERROR).

### **4.7 JUnit Test Cases**
- Includes **unit tests** for Controller, Service, Utility, and Exception Handling.
- Uses **Mockito** to mock dependencies and isolate test scenarios.
- Validates API behavior, Selenium operations, and Excel file creation.
- Implements **integration tests** for verifying end-to-end workflow.

## **5. Execution Workflow**

1. **User Calls API** → Sends an HTTP request to `/api/scraper/search?keyword=India`.
2. **Controller Processes Request** → Calls `ScraperService` to execute the search.
3. **Service Performs Web Scraping** → Uses Selenium to extract search results.
4. **Data Stored in Excel** → `ScraperUtil` saves extracted data to an Excel file.
5. **Response Sent to User** → API returns success message or error details.
6. **Error Handling** → If any issue occurs, `GlobalExceptionHandler` catches and logs it.
7. **Tests Executed** → JUnit tests validate correctness of each component.

## **6. Deployment & Execution**

### **Step 1: Install ChromeDriver**

Download the latest **ChromeDriver** from:
🔗 [ChromeDriver Download](https://sites.google.com/chromium.org/driver/)

### **Step 2: Run the Spring Boot Application**

Use Maven to start the service:

```sh
mvn spring-boot:run
```

### **Step 3: Call the API**

Make an API request using **cURL** or Postman:

```sh
curl "http://localhost:8080/api/scraper/search?keyword=India"
```

### **Step 4: Run JUnit Tests**

Execute all test cases with:

```sh
mvn test
```

## **7. Conclusion**

This document provides a technical breakdown of the **Spring Boot Selenium API Scraper** project, explaining its architecture, dependencies, and execution flow. This implementation ensures efficient web scraping, proper logging, error handling, and unit testing, making it maintainable and scalable. 🚀

