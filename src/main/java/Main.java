import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpConnection connection = new HttpConnection();
        connection.connectWithNbp();











        CreateExcelFile createExcelFile = new CreateExcelFile();
        createExcelFile.createExcelFile();//Excel musi być tworzony na końcu żeby mógł wczytać wszystkie parametry
    }
}
