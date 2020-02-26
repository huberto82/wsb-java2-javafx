package controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

public class BasicControls extends Application {
    VBox root = new VBox();
    Scene scene = new Scene(root, 400,600, Color.AQUA);
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(scene);
        primaryStage.show();
        root.setSpacing(10);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.CENTER);

        Label label = new Label("Wpisz imię");
        root.getChildren().add(label);

        TextField textField = new TextField("ABC");
        root.getChildren().add(textField);
        textField.setMaxWidth(120);
        label.setLabelFor(textField);

        textField.setOnAction((event) -> {
            System.out.println(textField.getText());
        });

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        root.getChildren().add(textArea);

        Button button = new Button("OK");
        root.getChildren().add(button);
        button.setPrefWidth(80);
        button.setOnAction((event) -> {
            textArea.appendText("Kliknąłeś w przycisk\n");
        });

        CheckBox checkBox = new CheckBox("Aktywny");
        root.getChildren().add(checkBox);
        checkBox.setOnAction((event) -> {
            textArea.appendText(" CheckBox "+ checkBox.isSelected());
        });

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Poniedziałek", "Sobota", "Niedziela");
        root.getChildren().add(comboBox);

        DatePicker datePicker = new DatePicker(LocalDate.now());
        root.getChildren().add(datePicker);

    }

    public static void main(String[] args) {
        launch();
    }
}
