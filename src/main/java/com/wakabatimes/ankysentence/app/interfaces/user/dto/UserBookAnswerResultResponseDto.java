package com.wakabatimes.ankysentence.app.interfaces.user.dto;

import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import lombok.Data;

import java.util.Date;

@Data
public class UserBookAnswerResultResponseDto {
    private String id;
    private Integer score;
    private Date date;

    public UserBookAnswerResultResponseDto(AnswerResult answerResult){
        this.id = answerResult.getAnswerResultId().getValue();
        this.score = answerResult.getAnswerResultScore().getValue();
        this.date = answerResult.getAnswerResultDate().getValue();
    }
}
