package com.wakabatimes.ankysentence.app.domain.service.book;

import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import com.wakabatimes.ankysentence.app.domain.model.book.BookId;
import com.wakabatimes.ankysentence.app.domain.model.book.Books;
import com.wakabatimes.ankysentence.app.domain.model.user.UserId;

public interface BookService {
    /**
     * 保存
     * @param book
     */
    void save(Book book, UserId userId);

    /**
     * ユーザに紐づくbooks参照
     * @param userId
     * @return
     */
    Books list(UserId userId);

    /**
     * idから参照
     * @param bookId
     * @return
     */
    Book get(BookId bookId);

    /**
     * 更新
     * @param book
     */
    void update(Book book);

    /**
     * 削除
     * @param bookId
     */
    void delete(BookId bookId);
}
