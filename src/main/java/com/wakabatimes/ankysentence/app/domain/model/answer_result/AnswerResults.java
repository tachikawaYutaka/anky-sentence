package com.wakabatimes.ankysentence.app.domain.model.answer_result;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerResults {
    private List<AnswerResult> answerResults;

    public AnswerResults() {
        answerResults = new ArrayList<>();
    }

    public AnswerResults(List<AnswerResult> answerResults) {
        this.answerResults = new ArrayList<>(answerResults);
    }

    /**
     * answerResult add
     * @param answerResult
     * @throws RuntimeException
     */
    public void add(@NonNull AnswerResult answerResult) throws RuntimeException {
        this.answerResults.add(answerResult);
    }

    /**
     * answerResult count
     * @return Integer
     */
    public Integer size() {
        return this.answerResults.size();
    }

    /**
     * get list
     *
     * @return List<User>
     */
    public List<AnswerResult> list() {
        return Collections.unmodifiableList(this.answerResults);
    }
}
