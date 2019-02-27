package com.wakabatimes.ankysentence.app.domain.model.question;

import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;

public interface QuestionRepository {
    void save(Question question, CategoryId categoryId);

    Questions list(CategoryId categoryId);

    Question get(QuestionId questionId);

    void update(Question question);

    void delete(QuestionId questionId);
}
