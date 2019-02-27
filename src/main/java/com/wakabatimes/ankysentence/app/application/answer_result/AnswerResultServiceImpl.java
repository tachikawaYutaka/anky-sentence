package com.wakabatimes.ankysentence.app.application.answer_result;

import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResultRepository;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResults;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;
import com.wakabatimes.ankysentence.app.domain.service.answer_result.AnswerResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerResultServiceImpl implements AnswerResultService{
    @Autowired
    private AnswerResultRepository answerResultRepository;

    @Override
    public void save(AnswerResult answerResult, QuestionId questionId) {
        answerResultRepository.save(answerResult,questionId);
    }

    @Override
    public AnswerResults list(QuestionId questionId) {
        return answerResultRepository.list(questionId);
    }
}
