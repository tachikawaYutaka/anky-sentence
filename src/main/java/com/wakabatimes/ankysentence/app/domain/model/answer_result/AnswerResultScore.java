package com.wakabatimes.ankysentence.app.domain.model.answer_result;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class AnswerResultScore {
    Integer value;
    public AnswerResultScore(Integer value){
        this.value = value;
    }
}
