package org.example.quizapp.mapper;

import org.example.quizapp.dao.entity.Question;
import org.example.quizapp.dto.QuestionDto;
import org.example.quizapp.dto.QuestionDtoForUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionDto toDto(Question entity);
    Question toEntity(QuestionDto dto);
    List<Question> listToEntity(List<QuestionDto> dtos);
    List<QuestionDto> listToDto(List<Question> entities);
    List<QuestionDtoForUser> listToDtoForUser(List<Question> entities);
    void mapForUpdate(@MappingTarget Question entity,QuestionDto dto);
}
