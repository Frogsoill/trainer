package ru.spbu.frogsoill.domain.repo;

import ru.spbu.frogsoill.domain.model.OpenQuestionCard;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    List<OpenQuestionCard> findAll();

    Optional<OpenQuestionCard> findById(Long Id);

    void add(OpenQuestionCard task);

    void update(OpenQuestionCard task);

    void remove(Long id);
}
