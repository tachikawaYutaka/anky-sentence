package com.wakabatimes.ankysentence.app.domain.model.answer_result;

import java.util.Date;
import java.util.UUID;

public class AnswerResultFactory {
    public static AnswerResult create(AnswerResultScore answerResultScore){
        AnswerResultId answerResultId = new AnswerResultId(UUID.randomUUID().toString());
        Date date = new Date();
        AnswerResultDate answerResultDate = new AnswerResultDate(date);
        return new AnswerResult(answerResultId, answerResultScore,answerResultDate);
    }
}
