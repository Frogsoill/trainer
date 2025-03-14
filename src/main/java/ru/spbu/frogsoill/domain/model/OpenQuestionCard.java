package ru.spbu.frogsoill.domain.model;

import ru.spbu.frogsoill.domain.util.ValidationUtil;

public class OpenQuestionCard {
    private final String question;
    private final String expectedAnswer;

    public OpenQuestionCard(String question, String expectedAnswer) {
        ValidationUtil.validateNotEmpty(question, "Question is empty.");
        ValidationUtil.validateNotEmpty(expectedAnswer, "Expected answer is empty.");

        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(String answer) {
        ValidationUtil.validateNotEmpty(answer, "Answer is empty.");
        return answer.equals(expectedAnswer);
    }
}