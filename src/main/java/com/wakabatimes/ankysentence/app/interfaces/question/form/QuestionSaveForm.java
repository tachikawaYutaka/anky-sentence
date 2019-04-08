package com.wakabatimes.ankysentence.app.interfaces.question.form;

import lombok.Data;

@Data
public class QuestionSaveForm {
    private String userId;
    private String categoryId;
    private String title;
    private String answer;
}
