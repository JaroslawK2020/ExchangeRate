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
//        System.out.println("headers Value :" + headersValues);
        int rowNumber = 1;
        XSSFRow row = sheet.createRow(rowNumber);
        int cellNumber = 0;
        for (Object value:
                headersValues) {
            XSSFCell cell = row.createCell(cellNumber++);
            rowNumber++;
            if(value instanceof ArrayList){
                XSSFRow row1 = sheet.createRow(2);
                for (int i = 0; i < ((ArrayList<?>) value).size(); i++) {
                    XSSFCell cell1 = row1.createCell(i++);
                    cell1.setCellValue(((ArrayList<?>) value).get(i).toString());
                }
            }
            else if(value instanceof String){
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
