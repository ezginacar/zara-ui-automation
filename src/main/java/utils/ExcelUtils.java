package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static Workbook getWorkbook(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(Constants.FILE_PATH + fileName);
        return new XSSFWorkbook(fis);
    }

    public static String getCellData(String fileName, String sheetName, int rowNum, int colNum) throws IOException {
        try (Workbook workbook = getWorkbook(Constants.FILE_PATH + fileName)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            return cell.toString();
        }
    }

    public static List<String> getRowData(String fileName, String sheetName, int rowNum) throws IOException {
        List<String> rowData = new ArrayList<>();
        try (Workbook workbook = getWorkbook(Constants.FILE_PATH + fileName)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            for (Cell cell : row) {
                rowData.add(cell.toString());
            }
        }
        return rowData;
    }

    public static List<String> getColumnData(String fileName, String sheetName, int colNum) throws IOException {
        List<String> colData = new ArrayList<>();
        try (Workbook workbook = getWorkbook(Constants.FILE_PATH + fileName)) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                Cell cell = row.getCell(colNum);
                if (cell != null) {
                    colData.add(cell.toString());
                }
            }
        }
        return colData;
    }
}
