package com.wakabatimes.ankysentence.app.domain.service.book;

import com.wakabatimes.ankysentence.AnkySentenceApplication;
import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import com.wakabatimes.ankysentence.app.domain.model.book.BookFactory;
import com.wakabatimes.ankysentence.app.domain.model.book.BookName;
import com.wakabatimes.ankysentence.app.domain.model.book.Books;
import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.model.user.UserFactory;
import com.wakabatimes.ankysentence.app.domain.model.user.UserMailAddress;
import com.wakabatimes.ankysentence.app.domain.model.user.UserPassword;
import com.wakabatimes.ankysentence.app.domain.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AnkySentenceApplication.class)
public class BookServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JdbcOperations jdbcOperations; // 各テスト前処理用
    @Before
    public void eachBefore() {
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=0");
        jdbcOperations.execute("DELETE FROM anky_user");
        jdbcOperations.execute("DELETE FROM relate_user_hash_to_user");
        jdbcOperations.execute("DELETE FROM book");
        jdbcOperations.execute("DELETE FROM relate_book_to_user");
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=1");
    }

    @After
    public void eachAfter() {
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=0");
        jdbcOperations.execute("DELETE FROM anky_user");
        jdbcOperations.execute("DELETE FROM relate_user_hash_to_user");
        jdbcOperations.execute("DELETE FROM book");
        jdbcOperations.execute("DELETE FROM relate_book_to_user");
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=1");
    }

    @Test
    public void save(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);

        BookName bookName = new BookName("hogehoge");
        Book book = BookFactory.create(bookName);
        bookService.save(book,user.getUserId());
    }

    @Test
    public void list(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);

        BookName bookName = new BookName("hogehoge");
        Book book = BookFactory.create(bookName);
        bookService.save(book,user.getUserId());

        BookName bookName2 = new BookName("hogehoge2");
        Book book2 = BookFactory.create(bookName2);
        bookService.save(book2,user.getUserId());

        Books books = bookService.list(user.getUserId());
        Assert.assertTrue(books.size() > 0 );
    }

    @Test
    public void get(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);

        BookName bookName = new BookName("hogehoge");
        Book book = BookFactory.create(bookName);
        bookService.save(book,user.getUserId());

        Book book1 = bookService.get(book.getBookId());
        Assert.assertTrue(book1.getBookId().getValue().equals(book.getBookId().getValue()));
    }

    @Test
    public void update(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);

        BookName bookName = new BookName("hogehoge");
        Book book = BookFactory.create(bookName);
        bookService.save(book,user.getUserId());

        Book book1 = bookService.get(book.getBookId());
        BookName bookName1 = new BookName("hogehoge2");
        Book book2 = new Book(book1.getBookId(),bookName1,book1.getBookSortNumber());
        bookService.update(book2);
    }

    @Test
    public void delete(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);

        BookName bookName = new BookName("hogehoge");
        Book book = BookFactory.create(bookName);
        bookService.save(book,user.getUserId());

        bookService.delete(book.getBookId());
    }


}
