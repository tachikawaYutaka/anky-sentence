package com.wakabatimes.ankysentence.app.infrastructure.answer_result.dto;

import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import lombok.Data;

import java.util.Date;

@Data
public class AnswerResultDto {
    private String id;
    private Integer score;
    private Date created;

    public AnswerResultDto(){}

    public AnswerResultDto(AnswerResult answerResult) {
        this.id = answerResult.getAnswerResultId().getValue();
        this.score = answerResult.getAnswerResultScore().getValue();
        this.created = answerResult.getAnswerResultDate().getValue();
    }
}
