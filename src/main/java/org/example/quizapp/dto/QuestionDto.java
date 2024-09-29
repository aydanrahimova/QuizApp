package org.example.quizapp.dto;

import lombok.Data;
import org.example.quizapp.enums.Category;
import org.example.quizapp.enums.Level;

@Data
public class QuestionDto {
    private Category category;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private Level difficultyLevel;
}
