package org.example.quizapp.controller;

import org.example.quizapp.dto.QuestionDto;
import org.example.quizapp.enums.Category;
import org.example.quizapp.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("{category}")
    public List<QuestionDto> getByCategory(@PathVariable Category category) {
        return questionService.getByCategory(category);
    }

    @GetMapping("{id}")
    public QuestionDto getById(@PathVariable Long id) {
        return questionService.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDto dto) {
        return questionService.addQuestion(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto dto) {
        return questionService.updateQuestion(id, dto);
    }
}
