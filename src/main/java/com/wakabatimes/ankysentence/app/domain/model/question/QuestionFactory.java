package com.wakabatimes.ankysentence.app.domain.model.question;

import java.util.UUID;

public class QuestionFactory {
    public static Question create(QuestionTitle questionTitle, QuestionAnswer questionAnswer){
        QuestionId questionId = new QuestionId(UUID.randomUUID().toString());
        return new Question(questionId, questionTitle,questionAnswer );
    }
    public static Question createWithSort(QuestionTitle questionTitle, QuestionAnswer questionAnswer, QuestionSortNumber questionSortNumber){
        QuestionId questionId = new QuestionId(UUID.randomUUID().toString());
        return new Question(questionId, questionTitle, questionAnswer,questionSortNumber );
    }
}
