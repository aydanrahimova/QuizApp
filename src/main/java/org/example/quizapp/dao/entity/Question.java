package org.example.quizapp.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.quizapp.enums.Category;
import org.example.quizapp.enums.Level;

@Entity
@Table(name = "question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    @Enumerated(EnumType.STRING)
    private Level difficultyLevel;
}
