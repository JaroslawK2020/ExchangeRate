import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpConnection connection = new HttpConnection();
        connection.connectWithNbp();


        /*Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[] {1, "Amit", "Shukla"});
        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
        data.put("4", new Object[] {3, "John", "Adwards"});
        data.put("5", new Object[] {4, "Brian", "Schultz"});

        CreateMySheet map = new CreateMySheet();
        map.createMySheet("test");
        map.createMyRowFromArray(data);*/









        CreateExcelFile createExcelFile = new CreateExcelFile();
        createExcelFile.createExcelFile();//Excel musi być tworzony na końcu żeby mógł wczytać wszystkie parametry
    }
}
