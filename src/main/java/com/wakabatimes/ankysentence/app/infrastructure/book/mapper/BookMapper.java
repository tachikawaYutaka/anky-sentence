package com.wakabatimes.ankysentence.app.infrastructure.book.mapper;

import com.wakabatimes.ankysentence.app.domain.model.user.UserId;
import com.wakabatimes.ankysentence.app.infrastructure.book.dto.BookDto;
import com.wakabatimes.ankysentence.app.infrastructure.book.dto.RelateBookToUserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Insert("INSERT \n" +
            "INTO book(id, name, sort_number, created, updated) \n" +
            "VALUES (#{id}, #{name}, #{sortNumber}, now(), now());")
    void save(BookDto bookDto);

    @Select("SELECT b.* FROM book b\n" +
            "LEFT JOIN relate_book_to_user rb ON b.id = rb.book_id\n" +
            "WHERE rb.user_id = #{userId}\n" +
            "ORDER BY b.sort_number;")
    List<BookDto> list(RelateBookToUserDto userId);

    @Select("SELECT * FROM book b\n" +
            "WHERE b.id = #{id};")
    BookDto get(BookDto bookDto);

    @Update("UPDATE book \n" +
            "SET\n" +
            "   name = #{name}\n" +
            "  , sort_number = #{sortNumber}\n" +
            "  , updated = now()\n" +
            "WHERE\n" +
            "  id = #{id};")
    void update(BookDto bookDto);

    @Delete("DELETE FROM book \n" +
            "WHERE id = #{id};")
    void delete(BookDto bookDto);
}
