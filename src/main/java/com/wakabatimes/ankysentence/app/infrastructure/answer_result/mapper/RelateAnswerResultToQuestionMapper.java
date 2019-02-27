package com.wakabatimes.ankysentence.app.infrastructure.answer_result.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.answer_result.dto.RelateAnswerResultToQuestionDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelateAnswerResultToQuestionMapper {
    @Insert("INSERT \n" +
            "INTO relate_answer_result_to_question(answer_result_id, question_id) \n" +
            "VALUES (#{answerResultId}, #{questionId});")
    void save(RelateAnswerResultToQuestionDto relateAnswerResultToQuestionDto);
}
