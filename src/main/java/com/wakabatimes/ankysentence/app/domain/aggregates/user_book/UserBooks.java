package com.wakabatimes.ankysentence.app.domain.aggregates.user_book;

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
}
