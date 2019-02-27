package com.wakabatimes.ankysentence.app.infrastructure.book;

import com.wakabatimes.ankysentence.app.domain.model.book.*;
import com.wakabatimes.ankysentence.app.domain.model.user.UserId;
import com.wakabatimes.ankysentence.app.infrastructure.book.dto.BookDto;
import com.wakabatimes.ankysentence.app.infrastructure.book.dto.RelateBookToUserDto;
import com.wakabatimes.ankysentence.app.infrastructure.book.mapper.BookMapper;
import com.wakabatimes.ankysentence.app.infrastructure.book.mapper.RelateBookToUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository{
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RelateBookToUserMapper relateBookToUserMapper;

    @Override
    public void save(Book book, UserId userId) {
        RelateBookToUserDto relateBookToUserDto = new RelateBookToUserDto();
        relateBookToUserDto.setUserId(userId.getValue());
        List<BookDto> bookDtos = bookMapper.list(relateBookToUserDto);
        Books books = new Books();
        int i = 0;
        for(BookDto bookDto : bookDtos) {
            BookId bookId = new BookId(bookDto.getId());
            BookName bookName = new BookName(bookDto.getName());
            BookSortNumber bookSortNumber = new BookSortNumber(bookDto.getSortNumber());
            Book book1 = new Book(bookId,bookName,bookSortNumber);
            books.add(book1);
            i++;
        }
        if(!books.containsName(book)){
            BookDto bookDto = new BookDto(book);
            bookDto.setSortNumber(i + 1);
            bookMapper.save(bookDto);
            RelateBookToUserDto relateBookToUserDto1 = new RelateBookToUserDto(book,userId);
            relateBookToUserMapper.save(relateBookToUserDto1);
        }else {
            throw new RuntimeException("重複しています");
        }
    }

    @Override
    public Books list(UserId userId) {
        RelateBookToUserDto relateBookToUserDto = new RelateBookToUserDto();
        relateBookToUserDto.setUserId(userId.getValue());
        List<BookDto> bookDtos = bookMapper.list(relateBookToUserDto);
        Books books = new Books();
        for(BookDto bookDto : bookDtos) {
            BookId bookId = new BookId(bookDto.getId());
            BookName bookName = new BookName(bookDto.getName());
            BookSortNumber bookSortNumber = new BookSortNumber(bookDto.getSortNumber());
            Book book = new Book(bookId,bookName,bookSortNumber);
            books.add(book);
        }
        return books;
    }

    @Override
    public Book get(BookId bookId) {
        BookDto bookDto = new BookDto();
        bookDto.setId(bookId.getValue());
        BookDto result = bookMapper.get(bookDto);
        if(result != null){
            BookId bookId1 = new BookId(result.getId());
            BookName bookName = new BookName(result.getName());
            BookSortNumber bookSortNumber = new BookSortNumber(result.getSortNumber());
            return new Book(bookId1,bookName,bookSortNumber);
        }else {
            throw new RuntimeException("指定したIDが存在しません。");
        }
    }

    @Override
    public void update(Book book) {
        BookDto bookDto = new BookDto(book);
        bookDto.setSortNumber(book.getBookSortNumber().getValue());
        bookMapper.update(bookDto);
    }

    @Override
    public void delete(BookId bookId) {
        BookDto bookDto = new BookDto();
        bookDto.setId(bookId.getValue());
        bookMapper.delete(bookDto);
    }
}
