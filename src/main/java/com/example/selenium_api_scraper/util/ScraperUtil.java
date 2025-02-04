package com.example.selenium_api_scraper.util;

import com.example.selenium_api_scraper.config.ExcelConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScraperUtil {
  private static final Logger logger = LoggerFactory.getLogger(ScraperUtil.class);
  private final ExcelConfig excelConfig;

  public ScraperUtil(ExcelConfig excelConfig) {
    this.excelConfig = excelConfig;
  }

  public void writeToExcel(String[][] data) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Search Results");

    Row headerRow = sheet.createRow(0);
    String[] headers = {"Search Keyword", "Search Result Title", "Search Result Link"};
    for (int i = 0; i < headers.length; i++) {
      headerRow.createCell(i).setCellValue(headers[i]);
    }

    for (int i = 0; i < data.length; i++) {
      Row row = sheet.createRow(i + 1);
      for (int j = 0; j < data[i].length; j++) {
        row.createCell(j).setCellValue(data[i][j]);
      }
    }

    try (FileOutputStream fileOut = new FileOutputStream(excelConfig.getFilePath())) {
      workbook.write(fileOut);
      logger.info("Excel file created successfully at {}", excelConfig.getFilePath());
    } catch (IOException e) {
      logger.error("Error writing to Excel file", e);
    } finally {
      try {
        workbook.close();
      } catch (IOException e) {
        logger.error("Error closing Excel workbook", e);
      }
    }
  }
}
