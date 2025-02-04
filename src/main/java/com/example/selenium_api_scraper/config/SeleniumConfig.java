package com.example.selenium_api_scraper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "selenium")
public class SeleniumConfig {
  private String driverPath;
  private String googleUrl;
  private boolean headless;
  private int waitTimeout;

  public String getDriverPath() { return driverPath; }
  public void setDriverPath(String driverPath) { this.driverPath = driverPath; }

  public String getGoogleUrl() { return googleUrl; }
  public void setGoogleUrl(String googleUrl) { this.googleUrl = googleUrl; }

  public boolean isHeadless() { return headless; }
  public void setHeadless(boolean headless) { this.headless = headless; }

  public int getWaitTimeout() { return waitTimeout; }
  public void setWaitTimeout(int waitTimeout) { this.waitTimeout = waitTimeout; }
}
