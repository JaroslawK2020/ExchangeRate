package ExchangeRate.FrontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(new File("src/main/java/ExchangeRate/FrontEnd/userWindow.fxml").toURI().toURL());
        Parent root = loader.load();

        stage.setTitle("Exchange rate");
        stage.setScene(new Scene(root, 1024,600));
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) throws IOException {

        launch(args);

        /*ChoseDateAndCurrency choseDateAndCurrency = new ChoseDateAndCurrency();
        HttpConnection connection = new HttpConnection();

        connection.connectWithNbp(choseDateAndCurrency.choseCurrency(), choseDateAndCurrency.choseStartDate(), choseDateAndCurrency.choseEndDate());

        HttpConnection connection1 = new HttpConnection();
        connection1.connectWithNbp(choseDateAndCurrency.choseCurrency(), choseDateAndCurrency.choseStartDate(), choseDateAndCurrency.choseEndDate());

        CreateExcelFile createExcelFile = new CreateExcelFile();
        createExcelFile.createAndOpenExcelFile();//Excel musi być tworzony na końcu żeby mógł wczytać wszystkie parametry*/
    }
}
