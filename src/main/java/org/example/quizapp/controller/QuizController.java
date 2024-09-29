package org.example.quizapp.controller;

import lombok.AllArgsConstructor;
import org.example.quizapp.dto.QuestionDtoForUser;
import org.example.quizapp.dto.ResponseDto;
import org.example.quizapp.enums.Category;
import org.example.quizapp.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("{id}")
    public ResponseEntity<List<QuestionDtoForUser>> getById(@PathVariable Long id) {
        return quizService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam Category category, @RequestParam Integer numOfQuestions, @RequestParam String title) {
        return quizService.create(category, numOfQuestions, title);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> calculateResult(@PathVariable Long id,@RequestBody List<ResponseDto> responses){
        return quizService.calculateResult(id,responses);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        quizService.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateById(@PathVariable Long id){
        quizService.updateById(id);
    }

}
