package com.wakabatimes.ankysentence.app.infrastructure.answer_result.mapper;

import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;
import com.wakabatimes.ankysentence.app.infrastructure.answer_result.dto.AnswerResultDto;
import com.wakabatimes.ankysentence.app.infrastructure.answer_result.dto.RelateAnswerResultToQuestionDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnswerResultMapper {
    @Insert("INSERT \n" +
            "INTO answer_result(id, score, created, updated) \n" +
            "VALUES (#{id}, #{score}, now(), now());")
    void save(AnswerResultDto answerResultDto);

    @Select("SELECT r.* FROM answer_result r\n" +
            "LEFT JOIN relate_answer_result_to_question rq ON r.id = rq.answer_result_id\n" +
            "WHERE rq.question_id = #{questionId}\n" +
            "ORDER BY r.created desc;")
    List<AnswerResultDto> list(RelateAnswerResultToQuestionDto questionId);
}
