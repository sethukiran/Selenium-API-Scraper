package com.example.selenium_api_scraper.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.selenium_api_scraper.config.SeleniumConfig;
import com.example.selenium_api_scraper.util.ScraperUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ScraperServiceTest {

  @Mock
  private SeleniumConfig seleniumConfig;

  @Mock
  private ScraperUtil scraperUtil;

  @InjectMocks
  private ScraperService scraperService;

  @Test
  void testScrapeGoogleResults_Success() {
    when(seleniumConfig.getGoogleUrl()).thenReturn("https://www.google.com");
    when(seleniumConfig.getDriverPath()).thenReturn("path/to/chromedriver.exe");

    String result = scraperService.scrapeGoogleResults("India");

    assertTrue(result.contains("Excel file created successfully.") || result.contains("Not enough search results found."));
  }

  @Test
  void testScrapeGoogleResults_Exception() {
    when(seleniumConfig.getGoogleUrl()).thenThrow(new RuntimeException("Invalid URL"));

    Exception exception = assertThrows(RuntimeException.class, () -> {
      scraperService.scrapeGoogleResults("India");
    });

    assertEquals("Invalid URL", exception.getMessage());
  }
}
