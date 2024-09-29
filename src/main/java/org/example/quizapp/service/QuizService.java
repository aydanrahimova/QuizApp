package org.example.quizapp.service;

import lombok.AllArgsConstructor;
import org.example.quizapp.dao.entity.Question;
import org.example.quizapp.dao.entity.Quiz;
import org.example.quizapp.dao.repository.QuestionRepository;
import org.example.quizapp.dao.repository.QuizRepository;
import org.example.quizapp.dto.QuestionDtoForUser;
import org.example.quizapp.dto.ResponseDto;
import org.example.quizapp.enums.Category;
import org.example.quizapp.mapper.QuestionMapper;
import org.example.quizapp.mapper.QuizMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public ResponseEntity<String> create(Category category, Integer numOfQuestions, String title) {
        String categoryStr = category.name();
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(categoryStr,numOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDtoForUser>> getById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        List<Question> questionsFromDB = quiz.getQuestions();
        List<QuestionDtoForUser> questionDtoForUsers = questionMapper.listToDtoForUser(questionsFromDB);
        return new ResponseEntity<>(questionDtoForUsers,HttpStatus.OK);
    }

    public void deleteById(Long id) {
        quizRepository.deleteById(id);
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<ResponseDto> responses) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        int result = 0;
        int i = 0;
        List<Question> questions = quiz.getQuestions();
        for(ResponseDto response: responses){
           if(response.getResponse().equals(questions.get(i).getRightAnswer()))
               result++;
           i++;
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    //That method will update only questions in quiz(not a title)
    public void updateById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Quiz not found with id: " + id));
        List<Question> questions = quiz.getQuestions();
        List<Question> newQuestions = questionRepository.findRandomQuestionsByCategory(questions.getFirst().getCategory().name(),questions.size());
        for(int i = 0;i<questions.size();i++){
            questions.set(i,newQuestions.get(i));
        }
        quizRepository.save(quiz);
    }
}
