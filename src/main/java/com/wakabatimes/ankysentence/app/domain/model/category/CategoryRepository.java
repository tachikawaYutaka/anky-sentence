package com.wakabatimes.ankysentence.app.domain.model.category;

import com.wakabatimes.ankysentence.app.domain.model.book.BookId;

public interface CategoryRepository {
    void save(Category category, BookId bookId);

    Categories list(BookId bookId);

    Category get(CategoryId categoryId);

    void update(Category category);

    void delete(CategoryId categoryId);
}
