package com.wakabatimes.ankysentence.app.interfaces.user.dto;

import com.wakabatimes.ankysentence.app.domain.aggregates.user_book.UserCategory;
import lombok.Data;

import java.util.List;

@Data
public class UserBookCategoryResponseDto {
    private String id;
    private String name;
    private Integer sort;
    private List<UserBookQuestionResponseDto> questions;

    public UserBookCategoryResponseDto(UserCategory userCategory){
        this.id = userCategory.getCategory().getCategoryId().getValue();
        this.name = userCategory.getCategory().getCategoryName().getValue();
        this.sort = userCategory.getCategory().getCategorySortNumber().getValue();
    }
}
