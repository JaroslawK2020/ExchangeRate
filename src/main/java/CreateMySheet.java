import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.*;
import java.util.stream.Collectors;

public class CreateMySheet {
    private CreateExcelFile excelFile = new CreateExcelFile();

    private XSSFSheet sheet;

    public void createMySheet(String sheetName){
        sheet = excelFile.returnWorkbook().createSheet(sheetName);
    }

    public void createHeaders(Map headersName){
        Set<String> headersSet = headersName.keySet();
//        System.out.println("headers keySet:" + headersSet);
        int rowNumber = 0;
            XSSFRow row = sheet.createRow(rowNumber);
            int cellNumber = 0;
            for (String key2:
                 headersSet) {
                XSSFCell cell = row.createCell(cellNumber++);
                cell.setCellValue(key2);
            }
    }

    public void createCell(Map headersName){
        Collection headersValues = headersName.values();
        int rowNumber = 1;
        int cellNumber = 0;
        XSSFRow row = sheet.createRow(rowNumber);
        for (Object value:
                headersValues) {
            XSSFCell cell;
            if(value instanceof ArrayList){
                for (int i = 0; i < ((ArrayList<?>) value).size(); i++) {
                    row = sheet.createRow(rowNumber+i+1);
//                    cell = row.createCell(3);
//                    cell.setCellValue(((ArrayList<?>) value).get(i).toString());
//                    System.out.println(((Map) ((ArrayList<?>) value).get(i)).entrySet());
                    row.createCell(3).setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("no").toString());
                    row.createCell(4).setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("effectiveDate").toString());
                    row.createCell(5).setCellValue(((Map) ((ArrayList<?>) value).get(i)).get("mid").toString());
                }
            }
            else if(value instanceof String){
                cell = row.createCell(cellNumber++);
                cell.setCellValue((String) value);
            }
        }
    }

    //działający kod na tworzenie Cell w 2 linii excela
    /*public void createCell(Map headersName){
        Collection headersValues = headersName.values();
//        System.out.println("headers Value :" + headersValues);
        int rowNumber = 1;
        XSSFRow row = sheet.createRow(rowNumber);
        int cellNumber = 0;
        for (Object value:
                headersValues) {
            XSSFCell cell = row.createCell(cellNumber++);
            if(cellNumber == 4){//cellNumber 4 to ArrayList które chcę zaimplementować jak mapę
                break;
            }
            cell.setCellValue(value.toString());
        }
    }*/

    /*public void createCellsFromArray(ArrayList ratesArray){
//        System.out.println("ratesArray Class" + ratesArray.getClass());
//        System.out.println("ratesArray get(0) Class " + ratesArray.get(0).getClass());
        int rowNum = 1;
        for (int i = 0; i < ratesArray.size(); i++) {
            System.out.println("createCellsFromArray i: " + i + " " + ratesArray.get(i).getClass());
            HashMap hashMap = (HashMap) ratesArray.get(i);
            for (Object entry:
                    hashMap.keySet()) {
                XSSFRow row = sheet.createRow(1);
                XSSFCell cell = row.createCell(rowNum++);
                cell.setCellValue(entry.toString());
            }
        }
    }*/


    public void createMyRowFromArray(Map data){
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            XSSFRow row = sheet.createRow(rownum++);
            Object [] objArr = (Object[]) data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
    }







    /*public void createExcel(){ //tworzy Excel nie wiem czy się tu przyda
        excelFile.createExcelFile();
    }*/
}
