package com.wakabatimes.ankysentence.app.domain.model.book;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * value object
 */
@Slf4j
@Value
public class BookName {
    String value;

    public BookName(String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(String value) {
        Integer count = value.length();
        if(count < 3 || count > 255) {
            throw new RuntimeException("ブック名が正しくありません。3～255文字で入力してください。");
        }

        String regex = "([<>&!?/\\\\@%.,$#()`:;{}+^*=~|\\[\\]\"'\\u0020])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        if (m.find()){
            throw new RuntimeException("ブック名が正しくありません。ハイフンとアンダーバー以外の半角記号は使用できません。");
        }
    }
}
