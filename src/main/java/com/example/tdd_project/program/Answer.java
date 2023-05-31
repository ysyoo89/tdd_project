package com.example.tdd_project.program;

public class Answer {
    private int i;
    private Question question;

    public Answer(Question question, int i) {
        this.question = question;
        this.i = i;
    }

    public Answer(Question characteristic, String matchingValue) {
        this.question = characteristic;
        this.i = characteristic.indexOf(matchingValue);
    }

    public String getQuestionText() {
        return question.getText();
    }

    @Override
    public String toString() {
        return String.format("%s %s", question.getText(), question.getAnswerChoice(i));
    }

    public boolean match(int expected) {
        return question.match(expected, i);
    }

    public boolean match(Answer otherAnswer) {
        if (otherAnswer == null) return false;

        return question.match(i, otherAnswer.i);
    }

    public Question getCharacteristic() {
        return question;
    }

    public Question getQuestion() {
        return question;
    }

}
