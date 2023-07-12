package com.example.quizlit;

public class QuizResult {
    private int questionNumber;
    private String userAnswer;
    private boolean isCorrect;

    public QuizResult(int questionNumber, String userAnswer, boolean isCorrect) {
        this.questionNumber = questionNumber;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
