package com.wakabatimes.ankysentence.app.domain.aggregates.examination;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class ExaminationInput {
    String value;

    public ExaminationInput(String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(String value) {
        Integer count = value.length();
        if(count < 3 || count > 100000) {
            throw new RuntimeException("入力が正しくありません。3～100000文字で入力してください。");
        }
    }
}
