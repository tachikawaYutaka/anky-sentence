package com.wakabatimes.ankysentence.app.interfaces.user.dto;

import com.wakabatimes.ankysentence.app.domain.aggregates.user_book.UserQuestion;
import lombok.Data;

import java.util.List;

@Data
public class UserBookQuestionResponseDto {
    private String id;
    private String title;
    private String answer;
    private Integer sort;
    private List<UserBookAnswerResultResponseDto> results;

    public UserBookQuestionResponseDto(UserQuestion userQuestion){
        this.id = userQuestion.getQuestion().getQuestionId().getValue();
        this.title = userQuestion.getQuestion().getQuestionTitle().getValue();
        this.answer = userQuestion.getQuestion().getQuestionAnswer().getValue();
        this.sort = userQuestion.getQuestion().getQuestionSortNumber().getValue();
    }
}
