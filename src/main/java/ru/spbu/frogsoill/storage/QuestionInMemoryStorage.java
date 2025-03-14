package ru.spbu.frogsoill.storage;

import org.springframework.stereotype.Repository;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.domain.repo.QuestionRepository;

import java.util.*;

@Repository
public class QuestionInMemoryStorage implements QuestionRepository {
    private final Map<Long, OpenQuestionCard> questions;

    public QuestionInMemoryStorage() {
        questions = new HashMap<>();
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        return questions.values().stream().toList();
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        OpenQuestionCard task = questions.get(id);
        if (Objects.nonNull(task)) return Optional.of(task);
        return Optional.empty();
    }

    @Override
    public void add(OpenQuestionCard task) {
        questions.put(task.getId(), task);
    }

    @Override
    public void update(OpenQuestionCard task) {
        questions.put(task.getId(), task);
    }

    @Override
    public void remove(Long id) {
        questions.remove(id);
    }
}
