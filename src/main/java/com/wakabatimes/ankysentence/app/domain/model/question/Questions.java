package com.wakabatimes.ankysentence.app.domain.model.question;

import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {
    private List<Question> questions;

    public Questions () {
        questions = new ArrayList<>();
    }

    public Questions(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
    }

    /**
     * categories add
     * @param question
     * @throws RuntimeException
     */
    public void add(@NonNull Question question) throws RuntimeException {
        this.questions.add(question);
    }

    /**
     * categories count
     * @return Integer
     */
    public Integer size() {
        return this.questions.size();
    }

    /**
     * get list
     *
     * @return List<User>
     */
    public List<Question> list() {
        return Collections.unmodifiableList(this.questions);
    }
}
