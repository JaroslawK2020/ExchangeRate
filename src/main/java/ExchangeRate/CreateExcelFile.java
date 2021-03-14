package ExchangeRate;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class CreateExcelFile {
    private final static XSSFWorkbook workbook = new XSSFWorkbook();

    public XSSFWorkbook returnWorkbook() {
        return workbook;
    }

    public void createAndOpenExcelFile() throws IOException //dokument excel musi być utworzony po przypisaniu mu parametrów. Bo plik excel się nie otworzy
    {
        try {
            File fileOut = new File("Exchange rate.xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
            workbook.write(fileOutputStream);
//            workbook.close();
            System.out.println("Excel created successfully");
            Desktop.getDesktop().open(new File(fileOut.getAbsolutePath()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
