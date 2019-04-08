package com.wakabatimes.ankysentence.app.interfaces.book;

import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import com.wakabatimes.ankysentence.app.domain.model.book.BookFactory;
import com.wakabatimes.ankysentence.app.domain.model.book.BookId;
import com.wakabatimes.ankysentence.app.domain.model.book.BookName;
import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.service.book.BookService;
import com.wakabatimes.ankysentence.app.interfaces.book.dto.BookResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.book.form.BookDeleteForm;
import com.wakabatimes.ankysentence.app.interfaces.book.form.BookSaveForm;
import com.wakabatimes.ankysentence.app.interfaces.book.form.BookUpdateForm;
import com.wakabatimes.ankysentence.app.interfaces.user.dto.UserUpdateResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.user.form.UserUpdateForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

import static com.wakabatimes.ankysentence.config.ApiUrlConstants.API_URL;

@Slf4j
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    private HttpStatus status = HttpStatus.OK;

    @PostMapping(value = API_URL + "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BookResponseDto save(Principal principal, @Valid @RequestBody BookSaveForm form){
        BookResponseDto bookResponseDto = new BookResponseDto();
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage("不正なアクセスです。");
            return bookResponseDto;
        }

        try{
            BookName bookName = new BookName(form.getName());
            Book book = BookFactory.create(bookName);
            bookService.save(book,user.getUserId());
            bookResponseDto.setId(book.getBookId().getValue());
            bookResponseDto.setName(book.getBookName().getValue());
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage("ブックを作成しました。");
        }catch (RuntimeException e){
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage(e.getMessage());
        }
        return bookResponseDto;
    }

    @PostMapping(value = API_URL + "/books/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BookResponseDto update(@PathVariable String bookId,  Principal principal, @Valid @RequestBody BookUpdateForm form){
        BookResponseDto bookResponseDto = new BookResponseDto();
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage("不正なアクセスです。");
            return bookResponseDto;
        }

        try{
            BookId bookId1 = new BookId(bookId);
            Book getBook = bookService.get(bookId1);
            BookName bookName = new BookName(form.getName());
            Book book = new Book(bookId1,bookName,getBook.getBookSortNumber());
            bookService.update(book);
            bookResponseDto.setId(book.getBookId().getValue());
            bookResponseDto.setName(book.getBookName().getValue());
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage("ブックを更新しました。");
        }catch (RuntimeException e){
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage(e.getMessage());
        }
        return bookResponseDto;
    }

    @PostMapping(value = API_URL + "/books/{bookId}/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BookResponseDto delete(@PathVariable String bookId,  Principal principal, @Valid @RequestBody BookDeleteForm form){
        BookResponseDto bookResponseDto = new BookResponseDto();
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage("不正なアクセスです。");
            return bookResponseDto;
        }

        try{
            BookId bookId1 = new BookId(bookId);
            bookService.delete(bookId1);
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage("ブックを更新しました。");
        }catch (RuntimeException e){
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            bookResponseDto.setStatus(status.getReasonPhrase());
            bookResponseDto.setMessage(e.getMessage());
        }
        return bookResponseDto;
    }
}
