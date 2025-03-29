package ru.spbu.frogsoill.api.mapper;

import org.mapstruct.Mapper;
import ru.spbu.frogsoill.api.dto.OpenQuestionCardDto;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionDtoMapper {

    OpenQuestionCard toModel(OpenQuestionCardDto dto);

    OpenQuestionCardDto toDto(OpenQuestionCard model);

    List<OpenQuestionCard> toModel(List<OpenQuestionCardDto> dto);

    List<OpenQuestionCardDto> toDto(List<OpenQuestionCard> model);
}