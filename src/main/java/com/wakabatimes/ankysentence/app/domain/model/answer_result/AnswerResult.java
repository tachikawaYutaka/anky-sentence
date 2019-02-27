package com.wakabatimes.ankysentence.app.domain.model.answer_result;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * root entity
 */
@Slf4j
@EqualsAndHashCode
public class AnswerResult {
    @Getter
    @NonNull
    AnswerResultId answerResultId;
    @Getter
    @NonNull
    AnswerResultScore answerResultScore;
    @Getter
    @NonNull
    AnswerResultDate answerResultDate;

    public AnswerResult(AnswerResultId answerResultId, AnswerResultScore answerResultScore, AnswerResultDate answerResultDate){
        this.answerResultId = answerResultId;
        this.answerResultScore = answerResultScore;
        this.answerResultDate = answerResultDate;
    }
}
