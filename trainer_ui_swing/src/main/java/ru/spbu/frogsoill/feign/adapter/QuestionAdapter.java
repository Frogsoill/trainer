package ru.spbu.frogsoill.feign.adapter;

import org.springframework.stereotype.Component;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.domain.repo.QuestionRepository;
import ru.spbu.frogsoill.feign.client.QuestionFeignClient;
import ru.spbu.frogsoill.feign.dto.OpenQuestionCardDto;
import ru.spbu.frogsoill.feign.mapper.QuestionDtoMapper;

import java.util.List;
import java.util.Optional;

@Component
public class QuestionAdapter implements QuestionRepository {

    private final QuestionFeignClient client;
    private final QuestionDtoMapper mapper;

    public QuestionAdapter(QuestionFeignClient client, QuestionDtoMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCardDto> questions = client.list();
        return questions.stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        return Optional.ofNullable(client.card(id)).map(mapper::toModel);
    }

    @Override
    public void add(OpenQuestionCard openQuestionCard) {
        client.add(mapper.toDto(openQuestionCard));
    }

    @Override
    public void update(OpenQuestionCard openQuestionCard) {
        client.update(mapper.toDto(openQuestionCard));
    }

    @Override
    public void remove(Long id) {
        client.remove(id);
    }
}