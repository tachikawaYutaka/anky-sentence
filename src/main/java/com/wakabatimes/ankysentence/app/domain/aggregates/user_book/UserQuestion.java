package com.wakabatimes.ankysentence.app.domain.aggregates.user_book;

import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResults;
import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import lombok.Getter;
import lombok.NonNull;

public class UserQuestion {
    @Getter
    @NonNull
    Question question;
    @Getter
    @NonNull
    AnswerResults answerResults;

    public UserQuestion(Question question, AnswerResults answerResults) {
        this.question = question;
        this.answerResults = answerResults;
    }
}
