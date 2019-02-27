package com.wakabatimes.ankysentence.app.domain.aggregates.user_book;

import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import lombok.Getter;
import lombok.NonNull;

public class UserBook {
    @Getter
    @NonNull
    Book book;
    @Getter
    @NonNull
    UserCategories userCategories;

    public UserBook(Book book,UserCategories userCategories){
        this.book = book;
        this.userCategories = userCategories;
    }
}
