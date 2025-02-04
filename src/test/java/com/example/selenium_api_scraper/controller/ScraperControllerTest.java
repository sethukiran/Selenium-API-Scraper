package com.example.selenium_api_scraper.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.selenium_api_scraper.service.ScraperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ScraperControllerTest {

  @Mock
  private ScraperService scraperService;

  @InjectMocks
  private ScraperController scraperController;

  @Test
  void testScrapeSuccess() {
    String keyword = "India";
    when(scraperService.scrapeGoogleResults(keyword)).thenReturn("Excel file created successfully.");

    String response = scraperController.scrape(keyword);
    assertEquals("Excel file created successfully.", response);
  }
}
