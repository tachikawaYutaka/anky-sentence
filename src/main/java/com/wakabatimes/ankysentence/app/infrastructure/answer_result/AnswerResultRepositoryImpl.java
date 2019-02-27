package com.wakabatimes.ankysentence.app.infrastructure.answer_result;

import com.wakabatimes.ankysentence.app.domain.model.answer_result.*;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;
import com.wakabatimes.ankysentence.app.infrastructure.answer_result.dto.AnswerResultDto;
import com.wakabatimes.ankysentence.app.infrastructure.answer_result.dto.RelateAnswerResultToQuestionDto;
import com.wakabatimes.ankysentence.app.infrastructure.answer_result.mapper.AnswerResultMapper;
import com.wakabatimes.ankysentence.app.infrastructure.answer_result.mapper.RelateAnswerResultToQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerResultRepositoryImpl implements AnswerResultRepository{
    @Autowired
    private AnswerResultMapper answerResultMapper;
    @Autowired
    private RelateAnswerResultToQuestionMapper relateAnswerResultToQuestionMapper;

    @Override
    public void save(AnswerResult answerResult, QuestionId questionId) {
        AnswerResultDto answerResultDto = new AnswerResultDto(answerResult);
        answerResultMapper.save(answerResultDto);
        RelateAnswerResultToQuestionDto relateAnswerResultToQuestionDto = new RelateAnswerResultToQuestionDto(answerResult,questionId);
        relateAnswerResultToQuestionMapper.save(relateAnswerResultToQuestionDto);
    }

    @Override
    public AnswerResults list(QuestionId questionId) {
        RelateAnswerResultToQuestionDto relateAnswerResultToQuestionDto = new RelateAnswerResultToQuestionDto();
        relateAnswerResultToQuestionDto.setQuestionId(questionId.getValue());
        List<AnswerResultDto> answerResultDtos = answerResultMapper.list(relateAnswerResultToQuestionDto);
        AnswerResults answerResults = new AnswerResults();
        for(AnswerResultDto answerResultDto : answerResultDtos){
            AnswerResultId answerResultId = new AnswerResultId(answerResultDto.getId());
            AnswerResultScore answerResultScore = new AnswerResultScore(answerResultDto.getScore());
            AnswerResultDate answerResultDate = new AnswerResultDate(answerResultDto.getCreated());
            AnswerResult answerResult = new AnswerResult(answerResultId,answerResultScore,answerResultDate);
            answerResults.add(answerResult);
        }
        return answerResults;
    }
}
