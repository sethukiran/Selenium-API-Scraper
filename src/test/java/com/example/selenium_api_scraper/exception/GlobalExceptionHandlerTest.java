package com.example.selenium_api_scraper.exception;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

  @Test
  void testHandleException() {
    Exception ex = new Exception("Test exception");
    ResponseEntity<String> response = exceptionHandler.handleException(ex);

    assertEquals(500, response.getStatusCodeValue());
    assertTrue(response.getBody().contains("Test exception"));
  }
}
