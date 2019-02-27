package com.wakabatimes.ankysentence.app.infrastructure.category.dto;

import com.wakabatimes.ankysentence.app.domain.model.book.BookId;
import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import lombok.Data;

@Data
public class RelateCategoryToBookDto {
    private String categoryId;
    private String bookId;

    public RelateCategoryToBookDto(){}

    public RelateCategoryToBookDto(Category category, BookId bookId) {
        this.categoryId = category.getCategoryId().getValue();
        this.bookId = bookId.getValue();
    }
}
