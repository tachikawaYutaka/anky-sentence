package com.wakabatimes.ankysentence.app.domain.service.category;

import com.wakabatimes.ankysentence.app.domain.model.book.BookId;
import com.wakabatimes.ankysentence.app.domain.model.category.Categories;
import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;

public interface CategoryService {
    /**
     * 保存
     * @param category
     */
    void save(Category category, BookId bookId);

    /**
     * bookIDに紐づくcategory参照
     * @param bookId
     * @return
     */
    Categories list(BookId bookId);

    /**
     * カテゴリIDから参照
     * @param categoryId
     * @return
     */
    Category get(CategoryId categoryId);

    /**
     * カテゴリ更新
     * @param category
     */
    void update(Category category);

    /**
     * カテゴリ削除
     * @param categoryId
     */
    void delete(CategoryId categoryId);
}
