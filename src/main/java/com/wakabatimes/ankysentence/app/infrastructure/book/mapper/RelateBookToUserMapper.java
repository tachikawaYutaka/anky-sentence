package com.wakabatimes.ankysentence.app.infrastructure.book.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.book.dto.RelateBookToUserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelateBookToUserMapper {
    @Insert("INSERT \n" +
            "INTO relate_book_to_user(book_id, user_id) \n" +
            "VALUES (#{bookId}, #{userId});")
    void save(RelateBookToUserDto relateBookToUserDto);
}
