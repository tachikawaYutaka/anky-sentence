package com.wakabatimes.ankysentence.app.interfaces.question.dto;

import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import lombok.Data;

@Data
public class QuestionResponseDto {
    private String id;
    private String title;
    private String answer;
    private String status;
    private String message;

    public QuestionResponseDto(Question question){
        this.id = question.getQuestionId().getValue();
        this.title = question.getQuestionTitle().getValue();
        this.answer = question.getQuestionAnswer().getValue();
    }
    public QuestionResponseDto(){}
}
