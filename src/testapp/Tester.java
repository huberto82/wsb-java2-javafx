package testapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

class Current {
    int index = 0;

    public void next() {
        index++;
    }

    public void prev() {
        index--;
    }

    public int get() {
        return index;
    }
}

public class Tester extends Application {
    VBox root = new VBox();
    HBox navigation = new HBox();
    VBox radios = new VBox();
    Button prevBtn = new Button("<< Poprzednie");
    Button nextBtn = new Button("Następne >>");
    Label info = new Label("Liczba poprawnych odpowiedzi: 0");
    TextArea question = new TextArea();
    RadioButton answer1 = new RadioButton();
    RadioButton answer2 = new RadioButton();
    RadioButton answer3 = new RadioButton();
    Button submitBtn = new Button("OK");
    List<TestItem> test = getTest();
    Current index = new Current();
    TestResult results = new TestResult(test);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, 600, 300);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        primaryStage.setScene(scene);
        primaryStage.show();
        root.getChildren().add(info);
        root.getChildren().add(navigation);
        root.getChildren().add(radios);
        navigation.getChildren().addAll(prevBtn, question, nextBtn);
        navigation.setSpacing(10);
        navigation.setAlignment(Pos.CENTER);
        radios.getChildren().addAll(answer1, answer2, answer3, submitBtn);
        radios.setSpacing(10);
        radios.setAlignment(Pos.CENTER);

        prevBtn.setOnAction((event) -> {
            if (index.get() - 1 >= 0) {
                clearAllRadios();
                enableAllRadios();
                unmarkAnswer();
                index.prev();
                question.setText(test.get(index.get()).getQuestion());
                answer1.setText(test.get(index.get()).getAnswer1());
                answer2.setText(test.get(index.get()).getAnswer2());
                answer3.setText(test.get(index.get()).getAnswer3());
                if (results.isAnswer(index.get())) {
                    markAnswer();
                    switch (results.getAnswer(index.get())) {
                        case 1:
                            answer1.setSelected(true);
                            break;
                        case 2:
                            answer2.setSelected(true);
                            break;
                        case 3:
                            answer3.setSelected(true);
                            break;
                    }
                    disableAllRadios();
                }
            }
        });

        nextBtn.setOnAction(event -> {
            if (index.get() + 1 < test.size()) {
                clearAllRadios();
                enableAllRadios();
                unmarkAnswer();
                index.next();
                question.setText(test.get(index.get()).getQuestion());
                answer1.setText(test.get(index.get()).getAnswer1());
                answer2.setText(test.get(index.get()).getAnswer2());
                answer3.setText(test.get(index.get()).getAnswer3());
                if (results.isAnswer(index.get())) {
                    markAnswer();
                    switch (results.getAnswer(index.get())) {
                        case 1:
                            answer1.setSelected(true);
                            break;
                        case 2:
                            answer2.setSelected(true);
                            break;
                        case 3:
                            answer3.setSelected(true);
                            break;
                    }
                    disableAllRadios();
                }
            }
        });

        submitBtn.setOnAction(event -> {
            int questionNumber = index.get();
            if (!results.isAnswer(questionNumber)) {
                int answerNumber = 0;
                if (answer1.isSelected()) {
                    answerNumber = 1;
                }
                if (answer2.isSelected()) {
                    answerNumber = 2;
                }
                if (answer3.isSelected()) {
                    answerNumber = 3;
                }
                results.put(questionNumber, answerNumber);
                disableAllRadios();
                updateInfo();
            }
        });

        EventHandler<ActionEvent> answerHandler = event -> {
            clearAllRadios();
            RadioButton btn = (RadioButton) event.getSource();
            btn.setSelected(true);
        };

        answer1.setOnAction(answerHandler);
        answer2.setOnAction(answerHandler);
        answer3.setOnAction(answerHandler);

        initApp();
    }

    void initApp() {
        question.setText(test.get(index.get()).getQuestion());
        answer1.setText(test.get(index.get()).getAnswer1());
        answer2.setText(test.get(index.get()).getAnswer2());
        answer3.setText(test.get(index.get()).getAnswer3());
    }

    List<TestItem> getTest() {
        List<TestItem> list = new ArrayList<>();
        list.add(new TestItem("Stolica Polski?", "Dąbrowa Górnicza", "Kraków", "Warszawa", 3));
        list.add(new TestItem("Liczba mieszkańców Dąbrowy Górniczej?", "112 000", "140 00", "160 000", 2));
        list.add(new TestItem("AAAA?", "112 000", "140 00", "160 000", 2));
        return list;
    }

    void clearAllRadios() {
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);
    }

    void disableAllRadios(){
        answer1.setDisable(true);
        answer2.setDisable(true);
        answer3.setDisable(true);
    }

    void enableAllRadios(){
        answer1.setDisable(false);
        answer2.setDisable(false);
        answer3.setDisable(false);
    }

    void unmarkAnswer(){
        answer1.setTextFill(Color.BLACK);
        answer2.setTextFill(Color.BLACK);
        answer3.setTextFill(Color.BLACK);
    }

    void markAnswer(){
        if (results.isAnswer(index.get())){
            int number = results.getAnswer(index.get());
            switch(number){
                case 1:
                    if (results.isValidAnswer(index.get())) {
                        answer1.setTextFill(Color.GREEN);
                    } else {
                        answer1.setTextFill(Color.RED);
                    }
                    break;
                case 2:
                    if (results.isValidAnswer(index.get())) {
                        answer2.setTextFill(Color.GREEN);
                    } else {
                        answer2.setTextFill(Color.RED);
                    }
                    break;
                case 3:
                    if (results.isValidAnswer(index.get())) {
                        answer3.setTextFill(Color.GREEN);
                    } else {
                        answer3.setTextFill(Color.RED);
                    }
                    break;
            }
        }
    }

    void updateInfo(){
        long valid = results.countOfValidAnswers();
        info.setText("Liczba poprawnych odpowiedzi: " + valid);
    }
}
