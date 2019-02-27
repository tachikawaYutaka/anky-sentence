package com.wakabatimes.ankysentence.app.infrastructure.category;

import com.wakabatimes.ankysentence.app.domain.model.book.BookId;
import com.wakabatimes.ankysentence.app.domain.model.category.*;
import com.wakabatimes.ankysentence.app.infrastructure.category.dto.CategoryDto;
import com.wakabatimes.ankysentence.app.infrastructure.category.dto.RelateCategoryToBookDto;
import com.wakabatimes.ankysentence.app.infrastructure.category.mapper.CategoryMapper;
import com.wakabatimes.ankysentence.app.infrastructure.category.mapper.RelateCategoryToBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RelateCategoryToBookMapper relateCategoryToBookMapper;

    @Override
    public void save(Category category, BookId bookId) {
        RelateCategoryToBookDto relateCategoryToBookDto = new RelateCategoryToBookDto();
        relateCategoryToBookDto.setBookId(bookId.getValue());
        List<CategoryDto> categoryDtoList = categoryMapper.list(relateCategoryToBookDto);
        Categories categories = new Categories();
        int i = 0;
        for(CategoryDto categoryDto : categoryDtoList){
            CategoryId categoryId = new CategoryId(categoryDto.getId());
            CategoryName categoryName = new CategoryName(categoryDto.getName());
            CategorySortNumber categorySortNumber = new CategorySortNumber(categoryDto.getSortNumber());
            Category category1 = new Category(categoryId,categoryName,categorySortNumber);
            categories.add(category1);
            i++;
        }
        if(!categories.containsName(category)) {
            CategoryDto categoryDto = new CategoryDto(category);
            categoryDto.setSortNumber(i + 1);
            categoryMapper.save(categoryDto);

            RelateCategoryToBookDto relateCategoryToBookDto1 = new RelateCategoryToBookDto(category,bookId);
            relateCategoryToBookMapper.save(relateCategoryToBookDto1);
        }else {
            throw new RuntimeException("重複しています");
        }
    }

    @Override
    public Categories list(BookId bookId) {
        RelateCategoryToBookDto relateCategoryToBookDto = new RelateCategoryToBookDto();
        relateCategoryToBookDto.setBookId(bookId.getValue());
        List<CategoryDto> categoryDtoList = categoryMapper.list(relateCategoryToBookDto);
        Categories categories = new Categories();
        for(CategoryDto categoryDto : categoryDtoList){
            CategoryId categoryId = new CategoryId(categoryDto.getId());
            CategoryName categoryName = new CategoryName(categoryDto.getName());
            CategorySortNumber categorySortNumber = new CategorySortNumber(categoryDto.getSortNumber());
            Category category = new Category(categoryId,categoryName,categorySortNumber);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public Category get(CategoryId categoryId) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId.getValue());
        CategoryDto result = categoryMapper.get(categoryDto);
        if(result != null){
            CategoryId categoryId1 = new CategoryId(result.getId());
            CategoryName categoryName = new CategoryName(result.getName());
            CategorySortNumber categorySortNumber = new CategorySortNumber(result.getSortNumber());
            return new Category(categoryId1,categoryName,categorySortNumber);
        }else {
            throw new RuntimeException("指定されたIDが存在しません");
        }
    }

    @Override
    public void update(Category category) {
        CategoryDto categoryDto = new CategoryDto(category);
        categoryDto.setSortNumber(category.getCategorySortNumber().getValue());
        categoryMapper.update(categoryDto);
    }

    @Override
    public void delete(CategoryId categoryId) {
        RelateCategoryToBookDto relateCategoryToBookDto = new RelateCategoryToBookDto();
        relateCategoryToBookDto.setCategoryId(categoryId.getValue());
        categoryMapper.delete(relateCategoryToBookDto);
    }
}
