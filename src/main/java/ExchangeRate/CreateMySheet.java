package ExchangeRate;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.text.CollationElementIterator;
import java.util.*;

public class CreateMySheet {
    private final CreateExcelFile excelFile = new CreateExcelFile();

    private XSSFSheet sheet;

    protected void createMySheet(String sheetName) {
        sheet = excelFile.returnWorkbook().createSheet(sheetName);
    }

    protected void createHeaders(Map headersName) {
        Set<String> headersSet = headersName.keySet();
        int rowNumber = 0;
        XSSFRow row = sheet.createRow(rowNumber);
        int cellNumber = 0;
//        set font style
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontName("Arial");
//        set cell style
        XSSFCellStyle style1 = sheet.getWorkbook().createCellStyle();
        style1.setFont(font);
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFColor headersColor = new XSSFColor(java.awt.Color.cyan);
        style1.setFillForegroundColor(headersColor);
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        set row style
        row.setHeight((short) 700);
//        set sheet style
        final int NUM_OF_CELLS = 6;
        for (int i = 0; i < NUM_OF_CELLS; i++) {
            sheet.setColumnWidth(i, 4500);
        }
        for (String key2 :
                headersSet) {
            XSSFCell cell = row.createCell(cellNumber++);
            cell.setCellValue(key2.toUpperCase());
            cell.setCellStyle(style1);
            if (key2.equals("rates")) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));
            }
        }
    }

    protected void createMyCells(Map headersName) {
        Collection headersValues = headersName.values();
        int rowNumber = 1;
        int cellNumber = 0;
        XSSFRow row = sheet.createRow(rowNumber);
//        set font style
        XSSFFont valuesFont = sheet.getWorkbook().createFont();
        valuesFont.setBold(true);
//        set cell style
        XSSFCellStyle valuesCellStyle = sheet.getWorkbook().createCellStyle();
        valuesCellStyle.setFont(valuesFont);
        valuesCellStyle.setAlignment(HorizontalAlignment.CENTER);
        valuesCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        set List cell style
        XSSFCellStyle listStyle = sheet.getWorkbook().createCellStyle();
        listStyle.setAlignment(HorizontalAlignment.CENTER);
        listStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        for (Object value :
                headersValues) {
            if (value instanceof ArrayList) {
                int columnIndex = 3;
                for (int i = 0; i < ((Map) ((ArrayList<?>) value).get(0)).keySet().size(); i++) {
                    List<String> list = new ArrayList<>(((Map) ((ArrayList<?>) value).get(0)).keySet());

                    XSSFCell cellListHeaders = row.createCell(columnIndex++);
                    cellListHeaders.setCellValue(list.get(i));
                    cellListHeaders.setCellStyle(valuesCellStyle);
                }
                for (int i = 0; i < ((ArrayList<?>) value).size(); i++) {
                    row = sheet.createRow(rowNumber + i + 1);

                    XSSFCell cellNo = row.createCell(3);
                    cellNo.setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("no").toString());
                    cellNo.setCellStyle(listStyle);

                    XSSFCell cellEffectiveDate = row.createCell(4);
                    cellEffectiveDate.setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("effectiveDate").toString());
                    cellEffectiveDate.setCellStyle(listStyle);

                    XSSFCell cellMid = row.createCell(5);
                    cellMid.setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("mid").toString());
                    cellMid.setCellStyle(listStyle);
                }
            } else if (value instanceof String) {
                XSSFCell valuesCell = row.createCell(cellNumber++);
                valuesCell.setCellValue((String) value);
                valuesCell.setCellStyle(valuesCellStyle);
            }
        }
    }
}
