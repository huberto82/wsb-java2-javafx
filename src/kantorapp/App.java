package kantorapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class App extends Application {
    GridPane root = new GridPane();
    Scene scene = new Scene(root,280,400);
    DatePicker datePicker = new DatePicker(LocalDate.now());
    Button downloadCurrencyListBtn = new Button("Pobierz");
    TextField amountField = new TextField();
    TextField resultField = new TextField();
    ListView<CurrencyRate> currencyListView = new ListView<>();


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Notowania walut");
        primaryStage.setResizable(false);
        root.setAlignment(Pos.CENTER);
        root.add(datePicker, 1, 1);
        root.add(downloadCurrencyListBtn,2 ,1);
        root.add(amountField,1, 2,2,1);
        root.add(resultField,1,3, 2, 1);
        root.add(currencyListView,1,4,2,1);
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(10));
        resultField.setEditable(false);

        amountField.setOnAction(event -> {
            if (currencyListView.getSelectionModel().getSelectedItem() == null){
                return;
            }
            double rate = currencyListView.getSelectionModel().getSelectedItem().getMid();
            try {
                double amount = Double.parseDouble(amountField.getText());
                double result = rate * amount;
                String amountInZL = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pl-PL")).format(result);
                resultField.setText(amountInZL);
            } catch (Exception e){

            }
        });

        downloadCurrencyListBtn.setOnAction(event -> {
            Task<String> downloadTask = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    LocalDate date = datePicker.getValue();
                    String dateStr = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
                    String urlStr  = String.format("http://api.nbp.pl/api/exchangerates/tables/A/%s?format=json", dateStr);
                    URL url = new URL(urlStr);
                    BufferedReader reader =
                            new BufferedReader(
                                    new InputStreamReader(
                                            url.openStream()
                                    )
                            );
                    return reader.readLine();
                }
            };
            downloadTask.setOnSucceeded(e -> {
                String rates = (String) e.getSource().getValue();
                List<CurrencyRate> list = mapToCurrencyRateList(rates);
                currencyListView.setItems(FXCollections.observableArrayList(list));
            });

            downloadTask.setOnFailed(e-> {
                datePicker.setValue(LocalDate.now());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nie udało się pobrać danych. Błędną data!!!");
                alert.show();
            });
            new Thread(downloadTask).start();
        });
    }

    List<CurrencyRate> mapToCurrencyRateList(String json){
        List<CurrencyRate> list = new ArrayList<>();
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonArray arr = reader.readArray();
        arr.getJsonObject(0).getJsonArray("rates").forEach(item ->{
            JsonObject obj = item.asJsonObject();
            String currency = obj.getString("currency");
            String code = obj.getString("code");
            double mid = obj.getJsonNumber("mid").doubleValue();
            CurrencyRate rate = new CurrencyRate(currency, code, mid);
            list.add(rate);
        });
        return list;
    }
}
