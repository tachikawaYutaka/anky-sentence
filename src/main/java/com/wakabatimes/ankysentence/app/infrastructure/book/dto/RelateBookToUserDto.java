package com.wakabatimes.ankysentence.app.infrastructure.book.dto;

import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import com.wakabatimes.ankysentence.app.domain.model.user.UserId;
import lombok.Data;

@Data
public class RelateBookToUserDto {
    private String bookId;
    private String userId;

    public RelateBookToUserDto(){}

    public RelateBookToUserDto(Book book, UserId userId) {
        this.bookId = book.getBookId().getValue();
        this.userId = userId.getValue();
    }
}
