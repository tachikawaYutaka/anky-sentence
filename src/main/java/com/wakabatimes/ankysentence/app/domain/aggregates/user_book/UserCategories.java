package com.wakabatimes.ankysentence.app.domain.aggregates.user_book;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserCategories {
    private List<UserCategory> userCategories;

    public UserCategories () {
        userCategories = new ArrayList<>();
    }

    public UserCategories(List<UserCategory> userCategories) {
        this.userCategories = new ArrayList<>(userCategories);
    }

    /**
     * menu add
     * @param userCategory
     * @throws RuntimeException
     */
    public void add(@NonNull UserCategory userCategory) throws RuntimeException {
        this.userCategories.add(userCategory);
    }

    /**
     * books count
     * @return Integer
     */
    public Integer size() {
        return this.userCategories.size();
    }

    /**
     * get list
     *
     * @return List<User>
     */
    public List<UserCategory> list() {
        return Collections.unmodifiableList(this.userCategories);
    }
}
