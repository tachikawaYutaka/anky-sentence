package com.wakabatimes.ankysentence.app.infrastructure.question.dto;

import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import lombok.Data;

@Data
public class QuestionDto {
    private String id;
    private String title;
    private String answer;
    private Integer sortNumber;

    public QuestionDto(){}

    public QuestionDto(Question question) {
        this.id = question.getQuestionId().getValue();
        this.title = question.getQuestionTitle().getValue();
        this.answer =  question.getQuestionAnswer().getValue();
    }
}
