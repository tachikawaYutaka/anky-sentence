package com.wakabatimes.ankysentence.app.domain.aggregates.user_book;

import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import lombok.Getter;
import lombok.NonNull;

public class UserCategory {
    @Getter
    @NonNull
    Category category;
    @Getter
    @NonNull
    UserQuestions userQuestions;

    public UserCategory(Category category, UserQuestions userQuestions){
        this.category = category;
        this.userQuestions = userQuestions;
    }
}
