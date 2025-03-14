package ru.spbu.frogsoill.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenQuestionCardTest {
    @Test
    @DisplayName("checkAnswer возвращает true при правильном ответе")
    void when_AnswerIsOk_then_checkAnswer_isTrue() {
        String question = "Вежливо попросить что то сделать на английском";
        String expectedAnswer = "Would You like to do something?";
        OpenQuestionCard card = new OpenQuestionCard(1L, question, expectedAnswer);
        assertTrue(card.checkAnswer("Would You like to do something?"));
    }

    @Test
    @DisplayName("checkAnswer возвращает false при неправильном ответе")
    void when_AnswerIsWrong_then_checkAnswer_isFalse() {
        String question = "Вежливо попросить что то сделать на английском";
        String expectedAnswer = "Would You like to do something?";
        OpenQuestionCard card = new OpenQuestionCard(1L, question, expectedAnswer);
        assertFalse(card.checkAnswer("London is the capital of Great Britain"));
    }

    @Test
    @DisplayName("checkAnswer бросает IllegalArgumentException при передаче null в ответе")
    void when_AnswerIsNull_then_checkAnswer_throwsNPE() {
        String question = "Вежливо попросить что то сделать на английском";
        String expectedAnswer = "Would You like to do something?";
        OpenQuestionCard card = new OpenQuestionCard(1L, question, expectedAnswer);
        assertThrows(IllegalArgumentException.class, () -> card.checkAnswer(null));
    }

    @Test
    @DisplayName("checkAnswer бросает IllegalArgumentException при передаче пустой строки в ответе")
    void when_AnswerIsEmpty_then_checkAnswer_throwsException() {
        String question = "Вежливо попросить что то сделать на английском";
        String expectedAnswer = "Would You like to do something?";
        OpenQuestionCard card = new OpenQuestionCard(1L, question, expectedAnswer);
        assertThrows(IllegalArgumentException.class, () -> card.checkAnswer(""));
    }

    @Test
    @DisplayName("checkAnswer бросает IllegalArgumentException при передаче строки с пробельными символами в ответе")
    void when_AnswerIsEmptySymbols_then_checkAnswer_throwsException() {
        String question = "Вежливо попросить что то сделать на английском";
        String expectedAnswer = "Would You like to do something?";
        OpenQuestionCard card = new OpenQuestionCard(1L, question, expectedAnswer);
        assertThrows(IllegalArgumentException.class, () -> card.checkAnswer("  \t\n"));
    }

    @Test
    @DisplayName("getQuestion возвращает строку переданную в конструкторе")
    void getQuestion_check() {
        String question = "Вежливо попросить что то сделать на английском";
        String expectedAnswer = "Would You like to do something?";
        OpenQuestionCard card = new OpenQuestionCard(1L, question, expectedAnswer);
        assertEquals("Вежливо попросить что то сделать на английском", card.getQuestion());
    }

    @Test
    @DisplayName("Регистр влияет на правильность ответа")
    public void testCheckAnswerCaseSensitivity() {
        OpenQuestionCard openQuestionCard = new OpenQuestionCard(1L, "What is the capital of France?", "Paris");
        assertFalse(openQuestionCard.checkAnswer("paris"));
    }
}