package ru.spbu.frogsoill.domain.service;

import org.springframework.stereotype.Service;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.domain.repo.QuestionRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<OpenQuestionCard> getAll() {
        return repository.findAll();
    }

    public Optional<OpenQuestionCard> getById(Long id) {
        if (Objects.isNull(id)) return Optional.empty();
        return repository.findById(id);
    }

    public boolean contains(OpenQuestionCard task) {
        if (isTaskInvalid(task)) {
            return false;
        }
        return repository.findById(task.getId()).isPresent();
    }

    public void save(OpenQuestionCard task) {
        if (isTaskInvalid(task)) return;
        if (contains(task)) repository.update(task);
        repository.add(task);
    }

    public void delete(OpenQuestionCard task) {
        if (isTaskInvalid(task)) return;
        repository.remove(task.getId());
    }


    private boolean isTaskInvalid(OpenQuestionCard task) {
        return Objects.isNull(task) || Objects.isNull(task.getId());
    }

}
