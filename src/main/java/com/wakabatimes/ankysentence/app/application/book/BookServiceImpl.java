package com.wakabatimes.ankysentence.app.application.book;

import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import com.wakabatimes.ankysentence.app.domain.model.book.BookId;
import com.wakabatimes.ankysentence.app.domain.model.book.BookRepository;
import com.wakabatimes.ankysentence.app.domain.model.book.Books;
import com.wakabatimes.ankysentence.app.domain.model.user.UserId;
import com.wakabatimes.ankysentence.app.domain.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void save(Book book, UserId userId) {
        bookRepository.save(book,userId);
    }

    @Override
    public Books list(UserId userId) {
        return bookRepository.list(userId);
    }

    @Override
    public Book get(BookId bookId) {
        return bookRepository.get(bookId);
    }

    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }

    @Override
    public void delete(BookId bookId) {
        bookRepository.delete(bookId);
    }
}
