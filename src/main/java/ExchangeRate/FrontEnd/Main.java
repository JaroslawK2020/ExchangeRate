package ExchangeRate.FrontEnd;

import ExchangeRate.ChoseDateAndCurrency;
import ExchangeRate.CreateExcelFile;
import ExchangeRate.HttpConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(new File("src/main/java/ExchangeRate/FrontEnd/userWindow.fxml").toURI().toURL());
        Parent root = loader.load();

        stage.setTitle("test");
        stage.setScene(new Scene(root, 800,500));
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
        createExcelFile.createExcelFile();//Excel musi być tworzony na końcu żeby mógł wczytać wszystkie parametry*/
    }
}