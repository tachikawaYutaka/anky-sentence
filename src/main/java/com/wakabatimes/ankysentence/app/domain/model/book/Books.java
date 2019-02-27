package com.wakabatimes.ankysentence.app.domain.model.book;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Books {
    private List<Book> books;

    public Books () {
        books = new ArrayList<>();
    }

    public Books(List<Book> books) {
        this.books = new ArrayList<>(books);
    }

    /**
     * add
     * @param book
     * @throws RuntimeException
     */
    public void add(@NonNull Book book) throws RuntimeException {
        if(this.containsName(book)) {
            throw new DuplicatedBookException(book.toString());
        }
        this.books.add(book);
    }

    /**
     * books count
     * @return Integer
     */
    public Integer size() {
        return this.books.size();
    }

    /**
     * containName
     * @param compare_item
     * @return Boolean
     */
    public boolean containsName(Book compare_item) {
        for(Book book : this.books) {
            if (book.getBookName().equals(compare_item.getBookName()) && !book.getBookId().equals(compare_item.getBookId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * get list
     *
     * @return List<User>
     */
    public List<Book> list() {
        return Collections.unmodifiableList(this.books);
    }
}
