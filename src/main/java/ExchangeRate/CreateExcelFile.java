package ExchangeRate;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcelFile {
    private final static XSSFWorkbook workbook = new XSSFWorkbook();

    public XSSFWorkbook returnWorkbook() {
        return workbook;
    }

    public void createExcelFile() throws IOException //dokument excel musi być utworzony po przypisaniu mu parametrów. Bo plik excel się nie otworzy
    {
        try {
            workbook.write(new FileOutputStream("Exchange rate.xlsx"));
            System.out.println("Excel created successfully");
        } catch (IOException e) {
            System.out.println("File not created!");
        }
        workbook.close();
    }
}
