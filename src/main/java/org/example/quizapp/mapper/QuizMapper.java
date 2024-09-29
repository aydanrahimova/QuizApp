package org.example.quizapp.mapper;

import org.example.quizapp.dao.entity.Question;
import org.example.quizapp.dao.entity.Quiz;
import org.example.quizapp.dto.QuestionDto;
import org.example.quizapp.dto.QuizDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    QuizDto toDto(Quiz entity);
    Quiz toEntity(QuizDto dto);
    List<Quiz> listToEntity(List<QuizDto> dtos);
    List<QuizDto> listToDto(List<Quiz> entities);

}
