package org.example.quizapp.dao.repository;

import org.example.quizapp.dao.entity.Question;
import org.example.quizapp.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllByCategory(Category category);

    @Query(value = "select * from question q where q.category=:category order by random() limit :numOfQuestions",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, Integer numOfQuestions);
}
