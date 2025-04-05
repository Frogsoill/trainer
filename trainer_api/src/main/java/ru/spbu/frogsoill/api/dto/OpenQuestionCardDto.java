package ru.spbu.frogsoill.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Карточка с вопросом")
public class OpenQuestionCardDto {
    @Schema(description = "ID вопроса", example = "1")
    private Long id;

    @Schema(description = "Вопрос", example = "Какая столица Зимбабве?")
    private String question;

    @Schema(description = "Ожидаемый ответ", example = "Хараре")
    private String expected_answer;


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

    public String getExpected_answer() {
        return expected_answer;
    }

    public void setExpected_answer(String expected_answer) {
        this.expected_answer = expected_answer;
    }

    public String displayedName() {
        return String.format("%s. %s", id, question);
    }
}
