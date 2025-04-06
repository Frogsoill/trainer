package ru.spbu.frogsoill.feign.dto;


public class OpenQuestionCardDto {
    private Long id;
    private String question;
    private String expectedAnswer;

    public OpenQuestionCardDto() {
    }

    public OpenQuestionCardDto(Long id, String question, String expectedAnswer) {
        this.id = id;
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setExpected_answer(String expected_answer) {
        this.expectedAnswer = expected_answer;
    }
}

