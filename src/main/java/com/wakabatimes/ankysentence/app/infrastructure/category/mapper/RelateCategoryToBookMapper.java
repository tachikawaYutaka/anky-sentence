package com.wakabatimes.ankysentence.app.infrastructure.category.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.category.dto.RelateCategoryToBookDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelateCategoryToBookMapper {
    @Insert("INSERT \n" +
            "INTO relate_category_to_book(category_id, book_id) \n" +
            "VALUES (#{categoryId}, #{bookId});")
    void save(RelateCategoryToBookDto relateCategoryToBookDto);
}
