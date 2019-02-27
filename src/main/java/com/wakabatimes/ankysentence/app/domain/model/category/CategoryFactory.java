package com.wakabatimes.ankysentence.app.domain.model.category;

import java.util.UUID;

public class CategoryFactory {
    public static Category create(CategoryName categoryName){
        CategoryId categoryId = new CategoryId(UUID.randomUUID().toString());
        return new Category(categoryId, categoryName);
    }
    public static Category createWithSort(CategoryName categoryName, CategorySortNumber categorySortNumber){
        CategoryId categoryId = new CategoryId(UUID.randomUUID().toString());
        return new Category(categoryId, categoryName, categorySortNumber);
    }
}
