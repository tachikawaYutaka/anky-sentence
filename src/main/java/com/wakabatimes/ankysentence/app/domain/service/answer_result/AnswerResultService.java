package com.wakabatimes.ankysentence.app.domain.service.answer_result;

import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResults;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;

public interface AnswerResultService {
    /**
     * 保存
     * @param answerResult
     */
    void save(AnswerResult answerResult, QuestionId questionId);

    /**
     * 問題に紐づくスコアを取得
     * @param questionId
     * @return
     */
    AnswerResults list(QuestionId questionId);
}
