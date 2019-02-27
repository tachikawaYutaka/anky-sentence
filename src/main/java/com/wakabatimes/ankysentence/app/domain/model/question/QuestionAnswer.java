package com.wakabatimes.ankysentence.app.domain.model.question;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * value object
 */
@Slf4j
@Value
public class QuestionAnswer {
    String value;

    public QuestionAnswer(String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(String value) {
        Integer count = value.length();
        if(count < 3 || count > 100000) {
            throw new RuntimeException("問題が正しくありません。3～100000文字で入力してください。");
        }
    }
}
