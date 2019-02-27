package com.wakabatimes.ankysentence.app.application.examination;

import com.wakabatimes.ankysentence.app.domain.aggregates.examination.Examination;
import com.wakabatimes.ankysentence.app.domain.aggregates.examination.ExaminationInput;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResultFactory;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResultScore;
import com.wakabatimes.ankysentence.app.domain.model.question.Question;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionAnswer;
import com.wakabatimes.ankysentence.app.domain.service.answer_result.AnswerResultService;
import com.wakabatimes.ankysentence.app.domain.service.examination.ExaminationService;
import com.wakabatimes.ankysentence.app.domain.service.question.QuestionService;
import org.apache.lucene.search.spell.LevensteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerResultService answerResultService;

    @Override
    public AnswerResult calculateConcordance(Examination examination) {
        Question question = questionService.get(examination.getQuestionId());
        QuestionAnswer questionAnswer = question.getQuestionAnswer();
        ExaminationInput examinationInput = examination.getExaminationInput();
        LevensteinDistance dis =  new LevensteinDistance();
        int result = (int) (dis.getDistance(questionAnswer.getValue(), examinationInput.getValue()) * 100);
        AnswerResultScore answerResultScore = new AnswerResultScore(result);
        AnswerResult answerResult = AnswerResultFactory.create(answerResultScore);
        answerResultService.save(answerResult,question.getQuestionId());
        return answerResult;
    }
}
