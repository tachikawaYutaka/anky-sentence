package com.wakabatimes.ankysentence.app.application.user_book;

import com.wakabatimes.ankysentence.app.domain.aggregates.user_book.*;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResults;
import com.wakabatimes.ankysentence.app.domain.model.book.Book;
import com.wakabatimes.ankysentence.app.domain.model.book.Books;
import com.wakabatimes.ankysentence.app.domain.model.category.Categories;
import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import com.wakabatimes.ankysentence.app.domain.model.question.Questions;
import com.wakabatimes.ankysentence.app.domain.model.user.UserId;
import com.wakabatimes.ankysentence.app.domain.service.answer_result.AnswerResultService;
import com.wakabatimes.ankysentence.app.domain.service.book.BookService;
import com.wakabatimes.ankysentence.app.domain.service.category.CategoryService;
import com.wakabatimes.ankysentence.app.domain.service.question.QuestionService;
import com.wakabatimes.ankysentence.app.domain.service.user.UserService;
import com.wakabatimes.ankysentence.app.domain.service.user_book.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookServiceImpl implements UserBookService{
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

    @Override
    public UserBooks getByUserId(UserId userId) {
        UserBooks userBooks = new UserBooks();
        Books books = bookService.list(userId);
        for(Book book : books.list()) {
            UserCategories userCategories = new UserCategories();
            Categories categories = categoryService.list(book.getBookId());
            for(Category category : categories.list()) {
                UserQuestions userQuestions = new UserQuestions();
                Questions questions = questionService.list(category.getCategoryId());
                for(Question question : questions.list()){
                    AnswerResults answerResults = answerResultService.list(question.getQuestionId());
                    UserQuestion userQuestion = new UserQuestion(question,answerResults);
                    userQuestions.add(userQuestion);
                }
                UserCategory userCategory = new UserCategory(category,userQuestions);
                userCategories.add(userCategory);
            }
            UserBook userBook = new UserBook(book,userCategories);
            userBooks.add(userBook);
        }
        return userBooks;
    }
}
