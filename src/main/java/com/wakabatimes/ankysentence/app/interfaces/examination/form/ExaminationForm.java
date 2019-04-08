package com.wakabatimes.ankysentence.app.interfaces.examination.form;

import lombok.Data;

@Data
public class ExaminationForm {
    private String userId;
    private String questionId;
    private String input;
}
