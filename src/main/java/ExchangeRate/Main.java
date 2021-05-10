package ExchangeRate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/userWindow.fxml"));

        stage.setTitle("Exchange rate");
        stage.setScene(new Scene(root, 1024, 600));
        stage.setResizable(false);
        stage.show();
    }

    /*public static void main(String[] args) {

//        launch(args);
    }*/

    void caller(String[] args){
        launch(args);
    }

}
