/**
 * Created under not commercial project
 */
package com.stingion.makeifine.data.service;

import com.stingion.makeifine.data.exception.QuestionNotFoundException;
import com.stingion.makeifine.data.model.Question;

import java.util.List;

public interface QuestionService {
    public Question create(Question question);

    public Question delete(int id) throws QuestionNotFoundException;

    public List<Question> findAll();

    public Question update(Question question) throws QuestionNotFoundException;

    public Question findById(int id);
}
