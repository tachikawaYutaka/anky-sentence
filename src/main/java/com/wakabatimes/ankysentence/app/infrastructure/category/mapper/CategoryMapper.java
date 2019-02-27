package com.wakabatimes.ankysentence.app.infrastructure.category.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.category.dto.CategoryDto;
import com.wakabatimes.ankysentence.app.infrastructure.category.dto.RelateCategoryToBookDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("INSERT \n" +
            "INTO category(id, name, sort_number, created, updated) \n" +
            "VALUES (#{id}, #{name}, #{sortNumber}, now(), now())")
    void save(CategoryDto categoryDto);

    @Select("SELECT c.* FROM category c\n" +
            "LEFT JOIN relate_category_to_book rc ON c.id = rc.category_id\n" +
            "WHERE rc.book_id = #{bookId}\n" +
            "ORDER BY c.sort_number;")
    List<CategoryDto> list(RelateCategoryToBookDto relateCategoryToBookDto);

    @Select("SELECT * from category WHERE id = #{id};")
    CategoryDto get(CategoryDto categoryDto);

    @Update("UPDATE category \n" +
            "SET\n" +
            "  name = #{name}\n" +
            "  , sort_number = #{sortNumber}\n" +
            "  , updated = now()\n" +
            "WHERE\n" +
            "  id = #{id};")
    void update(CategoryDto categoryDto);

    @Delete("DELETE FROM category WHERE id = #{categoryId};")
    void delete(RelateCategoryToBookDto relateCategoryToBookDto);
}
