/**
 * Created under not commercial project
 */
package com.stingion.makeifine.data.service;

import com.stingion.makeifine.data.exception.QuestionNotFoundException;
import com.stingion.makeifine.data.model.Question;
import com.stingion.makeifine.data.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionRepository questionRepository;

    @Override
    @Transactional
    public Question create(Question question) {
        Question createdQuestion = question;
        return questionRepository.save(createdQuestion);
    }

    @Override
    @Transactional
    public Question findById(int id) {
        return questionRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = QuestionNotFoundException.class)
    public Question delete(int id) throws QuestionNotFoundException {
        Question deletedQuestion = questionRepository.findOne(id);

        if (deletedQuestion == null)
            throw new QuestionNotFoundException();

        questionRepository.delete(deletedQuestion);
        return deletedQuestion;
    }

    @Override
    @Transactional
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = QuestionNotFoundException.class)
    public Question update(Question question) throws QuestionNotFoundException {
        Question updatedQuestion = questionRepository.findOne(question.getId());

        if (updatedQuestion == null)
            throw new QuestionNotFoundException();

        updatedQuestion.setQuestion(question.getQuestion());
        updatedQuestion.setVariant1(question.getVariant1());
        updatedQuestion.setVariant2(question.getVariant2());
        updatedQuestion.setVariant3(question.getVariant3());
        updatedQuestion.setVariant4(question.getVariant4());
        updatedQuestion.setAnswer(question.getAnswer());
        return updatedQuestion;
    }
}
