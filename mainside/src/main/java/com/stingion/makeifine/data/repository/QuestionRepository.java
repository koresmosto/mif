/**
 * Created under not commercial project
 */
package com.stingion.makeifine.data.repository;

import com.stingion.makeifine.data.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
