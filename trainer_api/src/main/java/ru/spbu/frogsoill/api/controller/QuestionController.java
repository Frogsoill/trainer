package ru.spbu.frogsoill.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.spbu.frogsoill.api.dto.OpenQuestionCardDto;
import ru.spbu.frogsoill.api.mapper.QuestionDtoMapper;
import ru.spbu.frogsoill.domain.service.QuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/question")
@Tag(name = "Questions", description = "Endpoints")
public class QuestionController {
    private final QuestionService service;
    private final QuestionDtoMapper mapper;

    public QuestionController(QuestionService service, QuestionDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Получение всех карточек", description = "Получены все карточки с вопросами")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OpenQuestionCardDto> getAll() {
        return mapper.toDto(service.getAll());
    }

    @Operation(summary = "Получение одной карточки по ID", description = "Получена карточка по её ID")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<OpenQuestionCardDto> getById(@Parameter(description = "ID искомой карточки") @PathVariable Long id) {
        return service.getById(id).map(mapper::toDto);
    }

    @Operation(summary = "Добавление новой карточки", description = "Создает новую карточку")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void save(@Parameter(description = "Новая карточка") @RequestBody OpenQuestionCardDto dto) {
        service.save(mapper.toModel(dto));
    }

    @Operation(summary = "Изменение карточки", description = "Изменяет существующую карточку")
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void update(@RequestBody OpenQuestionCardDto dto) {
        service.save(mapper.toModel(dto));
    }


    @Operation(summary = "Удаление карточки", description = "Находит карточку по ID и удаляет, если он найден")
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID карточки для удаления") @PathVariable Long id) {
        service.delete(id);
    }
}
