package com.wakabatimes.ankysentence.app.domain.service.category;

import com.wakabatimes.ankysentence.AnkySentenceApplication;
import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import com.wakabatimes.ankysentence.app.domain.model.book.BookFactory;
import com.wakabatimes.ankysentence.app.domain.model.book.BookName;
import com.wakabatimes.ankysentence.app.domain.model.category.Categories;
import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryFactory;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryName;
import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.model.user.UserFactory;
import com.wakabatimes.ankysentence.app.domain.model.user.UserMailAddress;
import com.wakabatimes.ankysentence.app.domain.model.user.UserPassword;
import com.wakabatimes.ankysentence.app.domain.service.book.BookService;
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
public class CategoryServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

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
        jdbcOperations.execute("DELETE FROM category");
        jdbcOperations.execute("DELETE FROM relate_category_to_book");
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=1");
    }

    @After
    public void eachAfter() {
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=0");
        jdbcOperations.execute("DELETE FROM anky_user");
        jdbcOperations.execute("DELETE FROM relate_user_hash_to_user");
        jdbcOperations.execute("DELETE FROM book");
        jdbcOperations.execute("DELETE FROM relate_book_to_user");
        jdbcOperations.execute("DELETE FROM category");
        jdbcOperations.execute("DELETE FROM relate_category_to_book");
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

        CategoryName categoryName = new CategoryName("category");
        Category category = CategoryFactory.create(categoryName);
        categoryService.save(category,book.getBookId());
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

        CategoryName categoryName = new CategoryName("category");
        Category category = CategoryFactory.create(categoryName);
        categoryService.save(category,book.getBookId());

        CategoryName categoryName2 = new CategoryName("category2");
        Category category2 = CategoryFactory.create(categoryName2);
        categoryService.save(category2,book.getBookId());

        Categories categories = categoryService.list(book.getBookId());
        Assert.assertTrue(categories.size() > 0);
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

        CategoryName categoryName = new CategoryName("category");
        Category category = CategoryFactory.create(categoryName);
        categoryService.save(category,book.getBookId());

        Category category1 = categoryService.get(category.getCategoryId());
        Assert.assertTrue(category.getCategoryId().getValue().equals(category1.getCategoryId().getValue()));
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

        CategoryName categoryName = new CategoryName("category");
        Category category = CategoryFactory.create(categoryName);
        categoryService.save(category,book.getBookId());

        Category category1 = categoryService.get(category.getCategoryId());

        CategoryName categoryName2 = new CategoryName("category2");
        Category category2 = new Category(category1.getCategoryId(),categoryName2,category1.getCategorySortNumber());
        categoryService.update(category2);
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

        CategoryName categoryName = new CategoryName("category");
        Category category = CategoryFactory.create(categoryName);
        categoryService.save(category,book.getBookId());

        Category category1 = categoryService.get(category.getCategoryId());
        categoryService.delete(category1.getCategoryId());
    }
}
