package com.wakabatimes.ankysentence.app.infrastructure.answer_result.dto;

import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;
import lombok.Data;

@Data
public class RelateAnswerResultToQuestionDto {
    private String answerResultId;
    private String questionId;

    public RelateAnswerResultToQuestionDto(){}

    public RelateAnswerResultToQuestionDto(AnswerResult answerResult, QuestionId questionId){
        this.answerResultId = answerResult.getAnswerResultId().getValue();
        this.questionId = questionId.getValue();
    }
}
