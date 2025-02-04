package com.example.selenium_api_scraper.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.selenium_api_scraper.config.ExcelConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.File;

@ExtendWith(MockitoExtension.class)
public class ScraperUtilTest {

  @Mock
  private ExcelConfig excelConfig;

  @InjectMocks
  private ScraperUtil scraperUtil;

  @Test
  void testWriteToExcel_Success() {
    when(excelConfig.getFilePath()).thenReturn("test_results.xlsx");

    String[][] testData = {{"Keyword", "Title", "URL"}};
    scraperUtil.writeToExcel(testData);

    File file = new File("test_results.xlsx");
    assertTrue(file.exists());

    file.delete();
  }
}
