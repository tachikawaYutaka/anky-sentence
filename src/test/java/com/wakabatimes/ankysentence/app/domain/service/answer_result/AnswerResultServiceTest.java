package com.wakabatimes.ankysentence.app.domain.service.answer_result;

import com.wakabatimes.ankysentence.AnkySentenceApplication;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResultFactory;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResultScore;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResults;
import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import com.wakabatimes.ankysentence.app.domain.model.book.BookFactory;
import com.wakabatimes.ankysentence.app.domain.model.book.BookName;
import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryFactory;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryName;
import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionAnswer;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionFactory;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionTitle;
import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.model.user.UserFactory;
import com.wakabatimes.ankysentence.app.domain.model.user.UserMailAddress;
import com.wakabatimes.ankysentence.app.domain.model.user.UserPassword;
import com.wakabatimes.ankysentence.app.domain.service.book.BookService;
import com.wakabatimes.ankysentence.app.domain.service.category.CategoryService;
import com.wakabatimes.ankysentence.app.domain.service.question.QuestionService;
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
public class AnswerResultServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerResultService answerResultService;

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
        jdbcOperations.execute("DELETE FROM question");
        jdbcOperations.execute("DELETE FROM relate_question_to_category");
        jdbcOperations.execute("DELETE FROM answer_result");
        jdbcOperations.execute("DELETE FROM relate_answer_result_to_question");
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
        jdbcOperations.execute("DELETE FROM question");
        jdbcOperations.execute("DELETE FROM relate_question_to_category");
        jdbcOperations.execute("DELETE FROM answer_result");
        jdbcOperations.execute("DELETE FROM relate_answer_result_to_question");
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

        QuestionTitle questionTitle = new QuestionTitle("hogehoge");
        QuestionAnswer questionAnswer = new QuestionAnswer("hogehogehoge");
        Question question = QuestionFactory.create(questionTitle,questionAnswer);
        questionService.save(question,category.getCategoryId());

        AnswerResultScore answerResultScore = new AnswerResultScore(64);
        AnswerResult answerResult = AnswerResultFactory.create(answerResultScore);
        answerResultService.save(answerResult,question.getQuestionId());
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

        QuestionTitle questionTitle = new QuestionTitle("hogehoge");
        QuestionAnswer questionAnswer = new QuestionAnswer("hogehogehoge");
        Question question = QuestionFactory.create(questionTitle,questionAnswer);
        questionService.save(question,category.getCategoryId());

        AnswerResultScore answerResultScore = new AnswerResultScore(64);
        AnswerResult answerResult = AnswerResultFactory.create(answerResultScore);
        answerResultService.save(answerResult,question.getQuestionId());

        AnswerResultScore answerResultScore2 = new AnswerResultScore(92);
        AnswerResult answerResult2 = AnswerResultFactory.create(answerResultScore2);
        answerResultService.save(answerResult2,question.getQuestionId());

        AnswerResults answerResults = answerResultService.list(question.getQuestionId());
        Assert.assertTrue(answerResults.size() > 0);
    }
}
