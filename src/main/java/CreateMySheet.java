import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import java.util.*;

public class CreateMySheet {
    private CreateExcelFile excelFile = new CreateExcelFile();

    private XSSFSheet sheet;


    public void createMySheet(String sheetName) {
        sheet = excelFile.returnWorkbook().createSheet(sheetName);
    }

    public void createHeaders(Map headersName) {
        Set<String> headersSet = headersName.keySet();
        int rowNumber = 0;
        XSSFRow row = sheet.createRow(rowNumber);
        int cellNumber = 0;
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setBold(true);
        XSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        for (String key2 :
                headersSet) {
            XSSFCell cell = row.createCell(cellNumber++);
            cell.setCellValue(key2);
            cell.setCellStyle(cellStyle);
        }
    }

    public void createMyCells(Map headersName) {
        Collection headersValues = headersName.values();
        int rowNumber = 1;
        int cellNumber = 0;
        XSSFRow row = sheet.createRow(rowNumber);
        for (Object value :
                headersValues) {
            XSSFCell cell;
            if (value instanceof ArrayList) {
                int columnIndex = 3;
                for (int i = 0; i < ((Map) ((ArrayList<?>) value).get(0)).keySet().size(); i++) {
                    List<String> list = new ArrayList<>(((Map) ((ArrayList<?>) value).get(0)).keySet());
                    row.createCell(columnIndex++).setCellValue(list.get(i));
                }
                for (int i = 0; i < ((ArrayList<?>) value).size(); i++) {
                    row = sheet.createRow(rowNumber + i + 1);
                    row.createCell(3).setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("no").toString());
                    row.createCell(4).setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("effectiveDate").toString());
                    row.createCell(5).setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("mid").toString());
                }
            } else if (value instanceof String) {
                cell = row.createCell(cellNumber++);
                cell.setCellValue((String) value);
            }
        }
    }

    public void createMyRowFromArray(Map data) {
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            XSSFRow row = sheet.createRow(rownum++);
            Object[] objArr = (Object[]) data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
    }







    /*public void createExcel(){ //tworzy Excel nie wiem czy się tu przyda
        excelFile.createExcelFile();
    }*/
}
