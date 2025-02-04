package com.example.selenium_api_scraper.controller;

import com.example.selenium_api_scraper.service.ScraperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scraper")
public class ScraperController {
  private static final Logger logger = LoggerFactory.getLogger(ScraperController.class);
  private final ScraperService scraperService;

  public ScraperController(ScraperService scraperService) {
    this.scraperService = scraperService;
  }

  @GetMapping("/search")
  public String scrape(@RequestParam String keyword) {
    logger.info("Received search request for keyword: {}", keyword);
    return scraperService.scrapeGoogleResults(keyword);
  }
}
