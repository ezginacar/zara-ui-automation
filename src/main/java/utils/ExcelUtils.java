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
    private Workbook workbook;

    public ExcelUtils (String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(Constants.FILE_PATH + fileName);
        this.workbook = new XSSFWorkbook(fis);
    }


    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        return cell.toString();
    }
   /*
    public static void main(String[] args) throws IOException {
        ExcelUtils e = new ExcelUtils("ZaraUITestDatas.xlsx");
        String keyword = e.getCellData( "SearchboxTest", 2, 2);
        System.out.println("Deneme -->  " + keyword);

    }
    */




}
