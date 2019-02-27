package com.wakabatimes.ankysentence.app.domain.model.book;

import com.wakabatimes.ankysentence.app.domain.model.user.UserId;

public interface BookRepository {
    void save(Book book, UserId userId);

    Books list(UserId userId);

    Book get(BookId bookId);

    void update(Book book);

    void delete(BookId bookId);
}
