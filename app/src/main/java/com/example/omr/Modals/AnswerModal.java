package com.example.omr.Modals;

public class AnswerModal {
    int questionNumber;
    Character correctAnswer, yourAnswer;
    Boolean correct;

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public AnswerModal(int questionNumber, Character correctAnswer, Character yourAnswer, Boolean correct) {
        this.questionNumber = questionNumber;
        this.correctAnswer = correctAnswer;
        this.yourAnswer = yourAnswer;
        this.correct = correct;
    }

    public AnswerModal() {
    }

    public Character getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Character correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Character getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(Character yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
