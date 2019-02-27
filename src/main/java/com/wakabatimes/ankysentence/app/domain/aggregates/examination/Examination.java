package com.wakabatimes.ankysentence.app.domain.aggregates.examination;

import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Examination {
    @Getter
    @NonNull
    QuestionId questionId;
    @Getter
    @NonNull
    ExaminationInput examinationInput;

    public Examination(QuestionId questionId, ExaminationInput examinationInput) {
        this.questionId = questionId;
        this.examinationInput = examinationInput;
    }
}
