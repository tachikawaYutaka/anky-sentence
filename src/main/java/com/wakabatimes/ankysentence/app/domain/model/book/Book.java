package com.wakabatimes.ankysentence.app.domain.model.book;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * root entity
 */
@Slf4j
@EqualsAndHashCode
public class Book {
    @Getter
    @NonNull
    BookId bookId;

    @Getter
    @NonNull
    BookName bookName;

    @Getter
    @NonNull
    BookSortNumber bookSortNumber;

    public Book(BookId bookId, BookName bookName){
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public Book(BookId bookId, BookName bookName, BookSortNumber bookSortNumber){
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookSortNumber = bookSortNumber;
    }
}
