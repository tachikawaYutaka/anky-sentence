package com.wakabatimes.ankysentence.app.application.question;

import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;
import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionRepository;
import com.wakabatimes.ankysentence.app.domain.model.question.Questions;
import com.wakabatimes.ankysentence.app.domain.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void save(Question question, CategoryId categoryId) {
        questionRepository.save(question,categoryId);
    }

    @Override
    public Questions list(CategoryId categoryId) {
        return questionRepository.list(categoryId);
    }

    @Override
    public Question get(QuestionId questionId) {
        return questionRepository.get(questionId);
    }

    @Override
    public void update(Question question) {
        questionRepository.update(question);
    }

    @Override
    public void delete(QuestionId questionId) {
        questionRepository.delete(questionId);
    }
}
