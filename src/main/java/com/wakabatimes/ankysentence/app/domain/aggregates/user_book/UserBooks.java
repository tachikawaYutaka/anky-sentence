package com.wakabatimes.ankysentence.app.domain.aggregates.user_book;

import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResults;
import com.wakabatimes.ankysentence.app.interfaces.user.dto.UserBookAnswerResultResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.user.dto.UserBookCategoryResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.user.dto.UserBookQuestionResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.user.dto.UserBookResponseDto;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserBooks {
    private List<UserBook> userBooks;

    public UserBooks () {
        userBooks = new ArrayList<>();
    }

    public UserBooks(List<UserBook> userBooks) {
        this.userBooks = new ArrayList<>(userBooks);
    }

    /**
     * menu add
     * @param userBook
     * @throws RuntimeException
     */
    public void add(@NonNull UserBook userBook) throws RuntimeException {
        this.userBooks.add(userBook);
    }

    /**
     * books count
     * @return Integer
     */
    public Integer size() {
        return this.userBooks.size();
    }

    /**
     * get list
     *
     * @return List<User>
     */
    public List<UserBook> list() {
        return Collections.unmodifiableList(this.userBooks);
    }

    /**
     * response list
     * @return List<UserBookResponseDto>
     */
    public List<UserBookResponseDto> responseList(){
        List<UserBookResponseDto> results = new ArrayList<>();

        List<UserBook> userBooks = Collections.unmodifiableList(this.userBooks);
        for(UserBook userBook : userBooks){
            UserBookResponseDto userBookResponseDto = new UserBookResponseDto(userBook);

            List<UserBookCategoryResponseDto> categories = new ArrayList<>();
            for(UserCategory userCategory :userBook.getUserCategories().list()){
                UserBookCategoryResponseDto userBookCategoryResponseDto = new UserBookCategoryResponseDto(userCategory);

                List<UserBookQuestionResponseDto> questions = new ArrayList<>();

                for( UserQuestion userQuestion : userCategory.getUserQuestions().list()){
                    UserBookQuestionResponseDto userBookQuestionResponseDto = new UserBookQuestionResponseDto(userQuestion);

                    List<UserBookAnswerResultResponseDto> answerResults = new ArrayList<>();
                    for(AnswerResult answerResult : userQuestion.getAnswerResults().list()){
                        UserBookAnswerResultResponseDto userBookAnswerResultResponseDto = new UserBookAnswerResultResponseDto(answerResult);
                        answerResults.add(userBookAnswerResultResponseDto);
                    }
                    userBookQuestionResponseDto.setResults(answerResults);
                    questions.add(userBookQuestionResponseDto);
                }

                userBookCategoryResponseDto.setQuestions(questions);
                categories.add(userBookCategoryResponseDto);
            }

            userBookResponseDto.setCategories(categories);
            results.add(userBookResponseDto);
        }
        return results;
    }
}
