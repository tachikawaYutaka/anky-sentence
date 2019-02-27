package com.wakabatimes.ankysentence.app.domain.model.answer_result;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * value object
 */
@Slf4j
@Value
public class AnswerResultDate {
    Date value;

    public AnswerResultDate(Date value){
        this.value = value;
    }
}
