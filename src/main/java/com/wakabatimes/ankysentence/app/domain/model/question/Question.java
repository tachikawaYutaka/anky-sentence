package com.wakabatimes.ankysentence.app.domain.model.question;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * root entity
 */
@Slf4j
@EqualsAndHashCode
public class Question {
    @Getter
    @NonNull
    QuestionId questionId;

    @Getter
    @NonNull
    QuestionTitle questionTitle;

    @Getter
    @NonNull
    QuestionAnswer questionAnswer;

    @Getter
    @NonNull
    QuestionSortNumber questionSortNumber;

    public Question(QuestionId questionId, QuestionTitle questionTitle, QuestionAnswer questionAnswer) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionAnswer = questionAnswer;
    }

    public Question(QuestionId questionId, QuestionTitle questionTitle, QuestionAnswer questionAnswer, QuestionSortNumber questionSortNumber) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionAnswer = questionAnswer;
        this.questionSortNumber = questionSortNumber;
    }
}
