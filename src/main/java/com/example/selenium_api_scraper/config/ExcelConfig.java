package com.example.selenium_api_scraper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "excel")
public class ExcelConfig {
  private String filePath;

  public String getFilePath() { return filePath; }
  public void setFilePath(String filePath) { this.filePath = filePath; }
}
