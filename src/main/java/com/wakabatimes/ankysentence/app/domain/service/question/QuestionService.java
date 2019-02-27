package com.wakabatimes.ankysentence.app.domain.service.question;

import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;
import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;
import com.wakabatimes.ankysentence.app.domain.model.question.Questions;

public interface QuestionService {
    /**
     * 保存
     * @param question
     */
    void save(Question question, CategoryId categoryId);

    /**
     * カテゴリに紐づく問題一覧
     * @param categoryId
     * @return
     */
    Questions list(CategoryId categoryId);

    /**
     * questionIdからの参照
     * @param questionId
     * @return
     */
    Question get(QuestionId questionId);

    /**
     * questionの更新
     * @param question
     */
    void update(Question question);

    /**
     * questionの削除
     * @param questionId
     */
    void delete(QuestionId questionId);
}
