package ExchangeRate.FrontEnd;

import ExchangeRate.CreateExcelFile;
import ExchangeRate.HttpConnection;
import ExchangeRate.ParsingApiResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {
//    error display
    @FXML
    public Label displayError = new Label();

    //Combo boxes
    @FXML
    public ComboBox<String> currencyCode1 = new ComboBox<>();
    @FXML
    public ComboBox<String> currencyCode2 = new ComboBox<>();
    @FXML
    public ComboBox<String> currencyCode3 = new ComboBox<>();
    @FXML
    public ComboBox<String> currencyCode4 = new ComboBox<>();

    ObservableList<String> currencyList = FXCollections.observableArrayList
            ("EUR", "USD", "CNY", "RUB", "BRL", "CAD", "GBP", "CZK", "JPY");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currencyCode1.setItems(currencyList);
        currencyCode2.setItems(currencyList);
        currencyCode3.setItems(currencyList);
        currencyCode4.setItems(currencyList);
    }


    private final HttpConnection connection1 = new HttpConnection();
    private final HttpConnection connection2 = new HttpConnection();
    private final HttpConnection connection3 = new HttpConnection();
    private final HttpConnection connection4 = new HttpConnection();


//    create currency instance

    private String currency1;
    private String currency2;
    private String currency3;
    private String currency4;

    public void setCurrency() {
        currency1 = currencyCode1.getValue();
        currency2 = currencyCode2.getValue();
        currency3 = currencyCode3.getValue();
        currency4 = currencyCode4.getValue();
    }

//    date range

    @FXML
    public DatePicker startDate = new DatePicker();

    public void disableStartDate() {
        try {
            startDate.setDayCellFactory(startDate -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate threeMonthEarlier = LocalDate.now().minusDays(93);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(threeMonthEarlier) < 0 || date.compareTo(today) > 0);
                }
            });
        } catch (NullPointerException e) {
            displayError.setText(e.getMessage());
        }
    }

    @FXML
    private DatePicker endDate;

    public void disableEndDate() {
        try {
            endDate.setDayCellFactory(endDate -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) > 0);
                }
            });
        } catch (NullPointerException e) {
            displayError.setText(e.getMessage());
        }
    }

    private LocalDate getStartDate;
    private LocalDate getEndDate;

    public void setDateRange() {
        getStartDate = startDate.getValue();
        getEndDate = endDate.getValue();
    }
//    create line chart

    @FXML
    public LineChart<String, Number> lineChart;

    private static Map json;

    public Map getJson() {
        return json;
    }

    public void setJson(Map json) {
        this.json = json;
    }

    public void createChart() {
        try {
            lineChart.getData().clear();//clearing line chart

            List<Object> currencyList = new ArrayList<>(); //add json to List
            connection1.connectWithNbp(currency1, getStartDate, getEndDate);
            currencyList.add(getJson());
            connection2.connectWithNbp(currency2, getStartDate, getEndDate);
            currencyList.add(getJson());
            connection3.connectWithNbp(currency3, getStartDate, getEndDate);
            currencyList.add(getJson());
            connection4.connectWithNbp(currency4, getStartDate, getEndDate);
            currencyList.add(getJson());

            XYChart.Series<String, Number> currencySeries1 = new XYChart.Series<>();
            XYChart.Series<String, Number> currencySeries2 = new XYChart.Series<>();
            XYChart.Series<String, Number> currencySeries3 = new XYChart.Series<>();
            XYChart.Series<String, Number> currencySeries4 = new XYChart.Series<>();

//        create line chart currency1
            for (int i = 0; i < ((ArrayList) ((Map) currencyList.get(0)).get("rates")).size(); i++) {
                currencySeries1.getData().add(new XYChart.Data<>(((String) ((Map) ((ArrayList) ((Map<?, ?>) currencyList.get(0)).get("rates")).get(i)).get("effectiveDate")),//pobieranie z listy currencyList pierwszej mapy, pobieranie listy wartości z klucza rates, lista ta zawiera mapę, pobieranie wartości z tej mapy tzn. daty
                        ((Double) ((Map) ((ArrayList) ((Map<?, ?>) currencyList.get(0)).get("rates")).get(i)).get("mid"))));////pobieranie z listy currencyList pierwszej mapy, pobieranie listy wartości z klucza rates, lista ta zawiera mapę, pobieranie wartości z tej mapy tzn. kursu waluty
            }
            currencySeries1.setName(((Map<?, ?>) currencyList.get(0)).get("currency").toString());

//        create line chart currency2
            for (int i = 0; i < ((ArrayList) ((Map) currencyList.get(1)).get("rates")).size(); i++) {
                currencySeries2.getData().add(new XYChart.Data<>(((String) ((Map) ((ArrayList) ((Map<?, ?>) currencyList.get(1)).get("rates")).get(i)).get("effectiveDate")),
                        ((Double) ((Map) ((ArrayList) ((Map<?, ?>) currencyList.get(1)).get("rates")).get(i)).get("mid"))));
            }
            currencySeries2.setName(((Map<?, ?>) currencyList.get(1)).get("currency").toString());

//        create line chart currency3
            for (int i = 0; i < ((ArrayList) ((Map) currencyList.get(2)).get("rates")).size(); i++) {
                currencySeries3.getData().add(new XYChart.Data<>(((String) ((Map) ((ArrayList) ((Map<?, ?>) currencyList.get(3)).get("rates")).get(i)).get("effectiveDate")),
                        ((Double) ((Map) ((ArrayList) ((Map<?, ?>) currencyList.get(2)).get("rates")).get(i)).get("mid"))));

            }
            currencySeries3.setName(((Map<?, ?>) currencyList.get(2)).get("currency").toString());

//        create line chart currency4
            for (int i = 0; i < ((ArrayList) ((Map) currencyList.get(3)).get("rates")).size(); i++) {
                currencySeries4.getData().add(new XYChart.Data<>(((String) ((Map) ((ArrayList) ((Map<?, ?>) currencyList.get(3)).get("rates")).get(i)).get("effectiveDate")),
                        ((Double) ((Map) ((ArrayList) ((Map<?, ?>) currencyList.get(3)).get("rates")).get(i)).get("mid"))));

            }
            currencySeries4.setName(((Map<?, ?>) currencyList.get(3)).get("currency").toString());

            lineChart.getData().addAll(currencySeries1, currencySeries2, currencySeries3, currencySeries4);

            for (final XYChart.Data<String, Number> currency1Data :
                    currencySeries1.getData()) {
                currency1Data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Tooltip.install(currency1Data.getNode(), new Tooltip(currency1Data.getXValue() + "\n" + currency1Data.getYValue()));
                    }
                });
            }

            for (final XYChart.Data<String, Number> currency2Data :
                    currencySeries2.getData()) {
                currency2Data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Tooltip.install(currency2Data.getNode(), new Tooltip(currency2Data.getXValue() + "\n" + currency2Data.getYValue()));
                    }
                });

            }

            for (final XYChart.Data<String, Number> currency3Data :
                    currencySeries3.getData()) {
                currency3Data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Tooltip.install(currency3Data.getNode(), new Tooltip(currency3Data.getXValue() + "\n" + currency3Data.getYValue()));
                    }
                });

            }

            for (final XYChart.Data<String, Number> currency4Data :
                    currencySeries4.getData()) {
                currency4Data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Tooltip.install(currency4Data.getNode(), new Tooltip(currency4Data.getXValue() + "\n" + currency4Data.getYValue()));
                    }
                });
            }
        } catch (Exception e) {
            displayError.setText(e.getMessage());
        }
    }

    //    open webpage
    public void openGitHub() {
        try {
            URI uri = new URI("https://github.com/JaroslawK2020/ExchangeRate.git");
            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");
        } catch (Exception e) {
            displayError.setText(e.getMessage());
        }
    }

    public void openNbpApi() {
        try {
            URI uri = new URI("http://api.nbp.pl/");
            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    open excel file
    public void openExcelFile() {
        try {
            ParsingApiResponse apiResponse1 = new ParsingApiResponse();
            ParsingApiResponse apiResponse2 = new ParsingApiResponse();
            ParsingApiResponse apiResponse3 = new ParsingApiResponse();
            ParsingApiResponse apiResponse4 = new ParsingApiResponse();
            connection1.connectWithNbp(currency1, getStartDate, getEndDate);
            apiResponse1.parsingJsonMap();
            connection2.connectWithNbp(currency2, getStartDate, getEndDate);
            apiResponse2.parsingJsonMap();
            connection3.connectWithNbp(currency3, getStartDate, getEndDate);
            apiResponse3.parsingJsonMap();
            connection4.connectWithNbp(currency4, getStartDate, getEndDate);
            apiResponse4.parsingJsonMap();

            CreateExcelFile createExcelFile = new CreateExcelFile();
            createExcelFile.createAndOpenExcelFile();

        } catch (IOException e) {
            displayError.setText(e.getMessage());
        }
    }
}
