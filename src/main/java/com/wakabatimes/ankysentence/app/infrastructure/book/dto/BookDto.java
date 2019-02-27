package com.wakabatimes.ankysentence.app.infrastructure.book.dto;

import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import lombok.Data;

@Data
public class BookDto {
    private String id;
    private String name;
    private Integer sortNumber;

    public BookDto(){}

    public BookDto(Book book) {
        this.id = book.getBookId().getValue();
        this.name = book.getBookName().getValue();
    }
}
