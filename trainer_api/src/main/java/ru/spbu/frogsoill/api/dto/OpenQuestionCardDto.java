package ru.spbu.frogsoill.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Карточка с вопросом")
public class OpenQuestionCardDto {
    @Schema(description = "ID вопроса", example = "1")
    private Long id;

    @Schema(description = "Вопрос", example = "Какая столица Зимбабве?")
    private String question;

    @Schema(description = "Ожидаемый ответ", example = "Хараре")
    private String expectedAnswer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setExpectedAnswer(String expected_answer) {
        this.expectedAnswer = expected_answer;
    }

    public String displayedName() {
        return String.format("%s. %s", id, question);
    }
}
