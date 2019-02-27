package com.wakabatimes.ankysentence.app.domain.model.book;

import java.util.UUID;

public class BookFactory {
    public static Book create(BookName bookName){
        BookId bookId = new BookId(UUID.randomUUID().toString());
        return new Book(bookId, bookName);
    }
    public static Book createWithSort(BookName bookName, BookSortNumber bookSortNumber){
        BookId bookId = new BookId(UUID.randomUUID().toString());
        return new Book(bookId, bookName, bookSortNumber);
    }
}
