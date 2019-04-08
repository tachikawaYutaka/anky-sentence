package com.wakabatimes.ankysentence.app.interfaces.question.form;

import lombok.Data;

@Data
public class QuestionUpdateForm {
    private String userId;
    private String title;
    private String answer;
}
