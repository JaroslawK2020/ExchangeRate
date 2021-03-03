import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ChoseDateAndCurrency choseDateAndCurrency = new ChoseDateAndCurrency();
        HttpConnection connection = new HttpConnection();

        connection.connectWithNbp(choseDateAndCurrency.choseCurrency(),choseDateAndCurrency.choseStartDate(),choseDateAndCurrency.choseEndDate());

        HttpConnection connection1 = new HttpConnection();
        connection1.connectWithNbp(choseDateAndCurrency.choseCurrency(), choseDateAndCurrency.choseStartDate(),choseDateAndCurrency.choseEndDate());

        CreateExcelFile createExcelFile = new CreateExcelFile();
        createExcelFile.createExcelFile();//Excel musi być tworzony na końcu żeby mógł wczytać wszystkie parametry
    }
}
