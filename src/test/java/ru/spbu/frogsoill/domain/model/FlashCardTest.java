package ru.spbu.frogsoill.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashCardTest {
    @Test
    @DisplayName("checkAnswer возвращает true при правильном ответе")
    void when_AnswerIsOk_then_checkAnswer_isTrue() {
        String question = "Вежливо попросить что то сделать на английском";
        String expectedAnswer = "Would You like to do something?";
        FlashCard card = new FlashCard(question, expectedAnswer);
        assertTrue(card.checkAnswer("Would You like to do something?"));
    }

}