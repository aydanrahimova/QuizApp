package org.example.quizapp.dao.repository;

import org.example.quizapp.dao.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
