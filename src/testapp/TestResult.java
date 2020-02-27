package testapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TestResult {
    private final List<TestItem> test;
    private final Map<Integer, Integer> answers = new HashMap<>();

    public TestResult(List<TestItem> test) {
        this.test = test;
    }

    public int getAnswer(int questionNumber){
        return answers.get(questionNumber);
    }

    public boolean isAnswer(int questionNumber){
        return answers.containsKey(questionNumber);
    }

    public boolean isValidAnswer(int questionNumber){
        return answers.get(questionNumber) == test.get(questionNumber).getAnswerNumber();
    }

    public void put(int qeustionNumber, int answerNumber){
        answers.put(qeustionNumber, answerNumber);
    }

    public long countOfValidAnswers(){
        return answers.keySet().stream().filter(this::isValidAnswer).count();
    }

    public long countOfInvalidAnswers(){
        return answers.keySet().size() - countOfValidAnswers();
    }

    public long countOfEmptyAnswers(){
        return test.size() - countOfValidAnswers() - countOfInvalidAnswers();
    }
}
