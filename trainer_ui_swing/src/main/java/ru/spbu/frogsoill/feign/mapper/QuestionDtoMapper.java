package ru.spbu.frogsoill.feign.mapper;

import org.mapstruct.Mapper;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.feign.dto.OpenQuestionCardDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionDtoMapper {

    OpenQuestionCard toModel(OpenQuestionCardDto dto);

    OpenQuestionCardDto toDto(OpenQuestionCard model);

    List<OpenQuestionCard> toModel(List<OpenQuestionCardDto> dto);

    List<OpenQuestionCardDto> toDto(List<OpenQuestionCard> model);
}