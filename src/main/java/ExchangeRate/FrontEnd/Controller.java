package ExchangeRate.FrontEnd;

import ExchangeRate.CreateExcelFile;
import ExchangeRate.HttpConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Combo boxes

    CreateExcelFile createExcelFile = new CreateExcelFile();

    @FXML
    public ComboBox<String> currencyCode1 = new ComboBox<>();
    @FXML
    public ComboBox<String> currencyCode2 = new ComboBox<>();
    @FXML
    public ComboBox<String> currencyCode3 = new ComboBox<>();
    @FXML
    public ComboBox<String> currencyCode4 = new ComboBox<>();

    ObservableList<String> currencyList = FXCollections.observableArrayList
            ("EUR","USD","CNY","RUB","BRL","CAD","GBP","CZK","JPY");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currencyCode1.setItems(currencyList);
        currencyCode2.setItems(currencyList);
        currencyCode3.setItems(currencyList);
        currencyCode4.setItems(currencyList);
    }


//    currency

    public String currency1;
    private String currency2;
    private String currency3;
    private String currency4;

    public void setCurrency(ActionEvent actionEvent){
        currency1 = currencyCode1.getValue();
        currency2 = currencyCode2.getValue();
        currency3 = currencyCode3.getValue();
        currency4 = currencyCode4.getValue();
        System.out.println(currency1);
        System.out.println(currency2);
        System.out.println(currency3);
        System.out.println(currency4);
    }

//    date range

    @FXML
    public DatePicker startDate = new DatePicker();

    public void disableStartDate(){
        startDate.setDayCellFactory(startDate->new DateCell(){
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date,empty);
                LocalDate threeMonthEarlier = LocalDate.now().minusDays(93);
                LocalDate today = LocalDate.now();
                setDisable(empty||date.compareTo(threeMonthEarlier) < 0||date.compareTo(today)>0);
            }
        });
    }

    @FXML
    private DatePicker endDate;

    public void disableEndDate(){
        endDate.setDayCellFactory(endDate->new DateCell(){
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date,empty);
                LocalDate today = LocalDate.now();
                setDisable(empty||date.compareTo(today) > 0);
            }
        });
    }

    private LocalDate setStartDate;
    private LocalDate setEndDate;

    public void setDateRange(ActionEvent actionEvent){
        setStartDate = startDate.getValue();
        setEndDate = endDate.getValue();

    }
//    chart

    @FXML
    public LineChart<String,Number> lineChart;

    public void createChart(ActionEvent actionEvent){//do poprawy
        /*System.out.println(currency1);
        System.out.println(setStartDate);
        System.out.println(setEndDate);
        HttpConnection connection = new HttpConnection();
        CreateExcelFile createExcelFile = new CreateExcelFile();
        connection.connectWithNbp(currency1,setStartDate,setEndDate);
//        createExcelFile.createAndOpenExcelFile();*/




        /*XYChart.Series<String,Number> dateSeries = new XYChart.Series<String,Number>();
        dateSeries.getData().add(new XYChart.Data<String,Number>(setStartDate.toString(),3.39));
        dateSeries.getData().add(new XYChart.Data<String,Number>("2021-03-09",2.90));
        dateSeries.getData().add(new XYChart.Data<String,Number>("2021-03-10",3.80));
        dateSeries.getData().add(new XYChart.Data<String,Number>(setEndDate.toString(),3.12));
        lineChart.getData().add(dateSeries);*/

    }

//    open webpage
    public void openGitHub(ActionEvent actionEvent){
        try{
            URI uri = new URI("https://github.com/JaroslawK2020/ExchangeRate.git");
            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openNbpApi(ActionEvent actionEvent){
        try{
            URI uri = new URI("http://api.nbp.pl/");
            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    open excel file
    public void openExcelFile(ActionEvent actionEvent) {


        HttpConnection connection1 = new HttpConnection();
        HttpConnection connection2 = new HttpConnection();
        HttpConnection connection3 = new HttpConnection();
        HttpConnection connection4 = new HttpConnection();
        try{
            CreateExcelFile createExcelFile = new CreateExcelFile();
            connection1.connectWithNbp(currency1,setStartDate,setEndDate);
            connection2.connectWithNbp(currency2,setStartDate,setEndDate);
            connection3.connectWithNbp(currency3,setStartDate,setEndDate);
            connection4.connectWithNbp(currency4,setStartDate,setEndDate);
            createExcelFile.createAndOpenExcelFile();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }




}
