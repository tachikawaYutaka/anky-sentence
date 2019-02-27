package com.wakabatimes.ankysentence.app.domain.model.category;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * value object
 */
@Slf4j
@Value
public class CategorySortNumber {
    Integer value;
    public CategorySortNumber(Integer value){
        this.value = value;
    }
}
