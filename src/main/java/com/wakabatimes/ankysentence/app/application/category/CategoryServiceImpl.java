package com.wakabatimes.ankysentence.app.application.category;

import com.wakabatimes.ankysentence.app.domain.model.book.BookId;
import com.wakabatimes.ankysentence.app.domain.model.category.Categories;
import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryRepository;
import com.wakabatimes.ankysentence.app.domain.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(Category category, BookId bookId) {
        categoryRepository.save(category,bookId);
    }

    @Override
    public Categories list(BookId bookId) {
        return categoryRepository.list(bookId);
    }

    @Override
    public Category get(CategoryId categoryId) {
        return categoryRepository.get(categoryId);
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category);
    }

    @Override
    public void delete(CategoryId categoryId) {
        categoryRepository.delete(categoryId);
    }
}
