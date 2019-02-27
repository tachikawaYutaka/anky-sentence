package com.wakabatimes.ankysentence.app.domain.aggregates.user_book;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserQuestions {
    private List<UserQuestion> userQuestions;

    public UserQuestions () {
        userQuestions = new ArrayList<>();
    }

    public UserQuestions(List<UserQuestion> userQuestions) {
        this.userQuestions = new ArrayList<>(userQuestions);
    }

    /**
     * menu add
     * @param userQuestion
     * @throws RuntimeException
     */
    public void add(@NonNull UserQuestion userQuestion) throws RuntimeException {
        this.userQuestions.add(userQuestion);
    }

    /**
     * books count
     * @return Integer
     */
    public Integer size() {
        return this.userQuestions.size();
    }

    /**
     * get list
     *
     * @return List<User>
     */
    public List<UserQuestion> list() {
        return Collections.unmodifiableList(this.userQuestions);
    }
}
