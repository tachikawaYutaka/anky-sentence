package com.wakabatimes.ankysentence.app.domain.model.category;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * root entity
 */
@Slf4j
@EqualsAndHashCode
public class Category {
    @Getter
    @NonNull
    CategoryId categoryId;
    @Getter
    @NonNull
    CategoryName categoryName;
    @Getter
    @NonNull
    CategorySortNumber categorySortNumber;

    public Category(CategoryId categoryId, CategoryName categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(CategoryId categoryId, CategoryName categoryName, CategorySortNumber categorySortNumber) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categorySortNumber = categorySortNumber;
    }
}
