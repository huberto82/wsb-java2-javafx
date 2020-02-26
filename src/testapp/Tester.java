package testapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

class Current {
    int index = 3;

    public void next(){
        index++;
    }

    public void prev(){
        index--;
    }

    public int get(){
        return index;
    }
}

public class Tester extends Application {
    VBox root = new VBox();
    HBox navigation = new HBox();
    VBox radios = new VBox();
    Button prevBtn = new Button("<< Poprzednie");
    Button nextBtn = new Button("Następne >>");
    TextArea question = new TextArea();
    RadioButton answer1 = new RadioButton();
    RadioButton answer2 = new RadioButton();
    RadioButton answer3 = new RadioButton();
    Button submitBtn = new Button("OK");

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, 600,300);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        primaryStage.setScene(scene);
        primaryStage.show();
        root.getChildren().add(navigation);
        root.getChildren().add(radios);
        navigation.getChildren().addAll(prevBtn, question, nextBtn);
        navigation.setSpacing(10);
        navigation.setAlignment(Pos.CENTER);
        radios.getChildren().addAll(answer1, answer2,answer3, submitBtn);
        radios.setSpacing(10);
        radios.setAlignment(Pos.CENTER);
        List<TestItem> test = getTest();
        Current index = new Current();
        prevBtn.setOnAction((event) ->{
            if (index.get() - 1 >= 0){
                index.prev();
                question.setText(test.get(index.get()).getQuestion());
                answer1.setText(test.get(index.get()).getAnswer1());
                answer2.setText(test.get(index.get()).getAnswer2());
                answer3.setText(test.get(index.get()).getAnswer3());
            }
        });

    }

    List<TestItem> getTest(){
        List<TestItem> list = new ArrayList<>();
        list.add(new TestItem("Stolica Polski?","Dąbrowa Górnicza", "Kraków", "Warszawa", 3));
        list.add(new TestItem("Liczba mieszkańców Dąbrowy Górniczej?","112 000", "140 00", "160 000", 2));
        list.add(new TestItem("AAAA?","112 000", "140 00", "160 000", 2));
        return list;
    }
}
