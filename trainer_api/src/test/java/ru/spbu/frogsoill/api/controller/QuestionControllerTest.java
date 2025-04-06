package ru.spbu.frogsoill.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.spbu.frogsoill.api.dto.OpenQuestionCardDto;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.domain.repo.QuestionRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuestionControllerTest {
    @MockitoBean
    private QuestionRepository taskRepository;

    @Autowired
    private QuestionController controller;

    @Test
    @DisplayName("Получение всех вопросов")
    void getAllTest() {
        Mockito.when(taskRepository.findAll())
                .thenReturn(List.of(new OpenQuestionCard(1L, "Вопрос?", "Ответ")));
        List<OpenQuestionCardDto> list = controller.getAll();
        Assertions.assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Получение всех вопросов")
    void getByIdTest() {
        Mockito.when(taskRepository.findById(1L))
                .thenReturn(Optional.of(new OpenQuestionCard(1L, "Вопрос?", "Ответ")));
        Mockito.when(taskRepository.findById(2L))
                .thenReturn(Optional.empty());
        Optional<OpenQuestionCardDto> card1 = controller.getById(1L);
        Optional<OpenQuestionCardDto> card2 = controller.getById(2L);
        Assertions.assertTrue(card1.isPresent());
        Assertions.assertTrue(card2.isEmpty());
    }
}