package ru.spbu.frogsoill.domain.model;

public class FlashCard {
    private final String question;
    private final String expectedAnswer;

    public FlashCard(String question, String expectedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(String answer) {
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Answer is null or empty");
        }
        return answer.equals(expectedAnswer);
    }
}