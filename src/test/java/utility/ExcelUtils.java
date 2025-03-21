package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;

    // Load Excel file
    public static void loadExcel(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new IOException("Sheet '" + sheetName + "' not found in file: " + filePath);
        }
        System.out.println("Excel loaded successfully. Sheet: " + sheet.getSheetName());
    }

    // Read data from Excel with null checks
    public static String getCellData(int row, int col) {
        if (sheet == null) {
            System.out.println("Error: Sheet is not loaded.");
            return "";
        }

        Row rowData = sheet.getRow(row);
        if (rowData == null) {
            System.out.println("Warning: Row " + row + " is empty or doesn't exist.");
            return "";
        }

        Cell cell = rowData.getCell(col);
        if (cell == null) {
            System.out.println("Warning: Cell at row " + row + ", column " + col + " is empty.");
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // Get row count
    public static int getRowCount() {
        return (sheet != null) ? sheet.getLastRowNum() : 0;
    }
}
