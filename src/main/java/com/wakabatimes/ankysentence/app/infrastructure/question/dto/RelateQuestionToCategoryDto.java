package com.wakabatimes.ankysentence.app.infrastructure.question.dto;

import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;
import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import lombok.Data;

@Data
public class RelateQuestionToCategoryDto {
    private String questionId;
    private String categoryId;

    public RelateQuestionToCategoryDto(){}

    public RelateQuestionToCategoryDto(Question question, CategoryId categoryId) {
        this.questionId = question.getQuestionId().getValue();
        this.categoryId = categoryId.getValue();
    }
}
