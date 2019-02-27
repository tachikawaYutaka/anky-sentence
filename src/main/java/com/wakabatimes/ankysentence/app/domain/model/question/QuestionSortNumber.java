package com.wakabatimes.ankysentence.app.domain.model.question;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * value object
 */
@Slf4j
@Value
public class QuestionSortNumber {
    Integer value;
    public QuestionSortNumber(Integer value){
        this.value = value;
    }
}
