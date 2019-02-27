package com.wakabatimes.ankysentence.app.domain.model.book;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * value object
 */
@Slf4j
@Value
public class BookSortNumber {
    Integer value;
    public BookSortNumber(Integer value){
        this.value = value;
    }
}
