package com.wakabatimes.ankysentence.app.domain.model.user;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * value object
 */
@Slf4j
@Value
public class UserHash {
    String value;

    public UserHash (String value) {
        validateHash(value);
        this.value = value;
    }

    private void validateHash(String value) {
        String inputPattern = "^[a-zA-Z0-9]+$";

        if(value.length() != 32){
            throw new RuntimeException("不正な値です。");
        }

        if (!Pattern.matches(inputPattern, value)) {
            throw new RuntimeException("不正な値です。");
        }
    }
}
