package org.example.quizapp.service;

import lombok.AllArgsConstructor;
import org.example.quizapp.dao.entity.Question;
import org.example.quizapp.dao.repository.QuestionRepository;
import org.example.quizapp.dto.QuestionDto;
import org.example.quizapp.enums.Category;
import org.example.quizapp.mapper.QuestionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public ResponseEntity<String> addQuestion(QuestionDto dto) {
        try {
            Question question = questionMapper.toEntity(dto);
            questionRepository.save(question);
            return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<QuestionDto> getByCategory(Category category) {
        return questionMapper.listToDto(questionRepository.findAllByCategory(category));
    }

    public QuestionDto getById(Long id) {
        return questionMapper.toDto(questionRepository.findById(id).orElseThrow());
    }

    public ResponseEntity<String> deleteQuestion(Long id) {
        try {
            Optional<Question> existQuestionOpt = questionRepository.findById(id);
            if(existQuestionOpt.isEmpty()){
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
            questionRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateQuestion(Long id,QuestionDto dto){
        try {
            Optional<Question> existingQuestionOpt = questionRepository.findById(id);
            if (existingQuestionOpt.isEmpty()) {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
            Question existingQuestion = existingQuestionOpt.get();
            questionMapper.mapForUpdate(existingQuestion,dto);
            questionRepository.save(existingQuestion);
            return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
