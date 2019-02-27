package com.wakabatimes.ankysentence.app.domain.model.answer_result;

import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;

public interface AnswerResultRepository {
    void save(AnswerResult answerResult, QuestionId questionId);

    AnswerResults list(QuestionId questionId);
}
