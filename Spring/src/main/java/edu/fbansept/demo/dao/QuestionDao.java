package edu.fbansept.demo.dao;

import edu.fbansept.demo.model.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Quizz, Integer> {

}
