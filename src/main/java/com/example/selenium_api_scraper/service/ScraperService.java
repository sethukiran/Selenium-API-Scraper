package com.example.selenium_api_scraper.service;

import com.example.selenium_api_scraper.config.SeleniumConfig;
import com.example.selenium_api_scraper.util.ScraperUtil;
import java.time.Duration;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Service
public class ScraperService {
  private static final Logger logger = LoggerFactory.getLogger(ScraperService.class);
  private final SeleniumConfig seleniumConfig;
  private final ScraperUtil scraperUtil;

  public ScraperService(SeleniumConfig seleniumConfig, ScraperUtil scraperUtil) {
    this.seleniumConfig = seleniumConfig;
    this.scraperUtil = scraperUtil;
  }

  public String scrapeGoogleResults(String searchKeyword) {
    System.setProperty("webdriver.chrome.driver", seleniumConfig.getDriverPath());

    ChromeOptions options = new ChromeOptions();
    if (seleniumConfig.isHeadless()) {
      options.addArguments("--headless");
    }

    WebDriver driver = new ChromeDriver(options);
    Duration duration = Duration.ofSeconds(seleniumConfig.getWaitTimeout());
    WebDriverWait wait = new WebDriverWait(driver, duration);

    try {
      driver.get(seleniumConfig.getGoogleUrl());
      WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
      searchBox.sendKeys(searchKeyword);
      searchBox.submit();

      wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3")));
      List<WebElement> results = driver.findElements(By.cssSelector("h3"));

      if (results.size() < 2) {
        return "Not enough search results found.";
      }

      String[][] scrapedData = new String[2][3];
      for (int i = 0; i < 2; i++) {
        WebElement titleElement = results.get(i);
        WebElement linkElement = titleElement.findElement(By.xpath("./ancestor::a"));
        scrapedData[i][0] = searchKeyword;
        scrapedData[i][1] = titleElement.getText();
        scrapedData[i][2] = linkElement.getAttribute("href");
      }

      scraperUtil.writeToExcel(scrapedData);
      return "Excel file created successfully.";
    } catch (Exception e) {
      logger.error("Error scraping Google results", e);
      return "Error scraping Google results: " + e.getMessage();
    } finally {
      driver.quit();
    }
  }
}
