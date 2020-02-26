package testapp;

public class TestItem {
    private final String question;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final int answerNumber;

    public TestItem(String question, String answer1, String asnwer2, String asnwer3, int answerNumber) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = asnwer2;
        this.answer3 = asnwer3;
        this.answerNumber = answerNumber;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }
}
