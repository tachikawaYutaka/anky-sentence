package com.wakabatimes.ankysentence.app.infrastructure.question.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.question.dto.QuestionDto;
import com.wakabatimes.ankysentence.app.infrastructure.question.dto.RelateQuestionToCategoryDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("INSERT \n" +
            "INTO question( \n" +
            "  id\n" +
            "  , title\n" +
            "  , answer\n" +
            "  , sort_number\n" +
            "  , created\n" +
            "  , updated\n" +
            ") \n" +
            "VALUES ( \n" +
            "  #{id}\n" +
            "  , #{title}\n" +
            "  , #{answer}\n" +
            "  , #{sortNumber}\n" +
            "  , now()\n" +
            "  , now()\n" +
            ")")
    void save(QuestionDto questionDto);

    @Select("SELECT q.* FROM question q\n" +
            "LEFT JOIN relate_question_to_category rq ON q.id = rq.question_id\n" +
            "WHERE rq.category_id = #{categoryId}\n" +
            "ORDER BY q.sort_number;")
    List<QuestionDto> list(RelateQuestionToCategoryDto relateQuestionToCategoryDto);

    @Select("SELECT * from question WHERE id = #{questionId}")
    QuestionDto get(RelateQuestionToCategoryDto relateQuestionToCategoryDto);

    @Update("UPDATE question \n" +
            "SET\n" +
            "   title = #{title}\n" +
            "  , answer = #{answer}\n" +
            "  , sort_number = #{sortNumber}\n" +
            "  , updated = now()\n" +
            "WHERE\n" +
            "  id = #{id}")
    void update(QuestionDto questionDto);

    @Delete("DELETE FROM question \n" +
            "WHERE id = #{questionId}")
    void delete(RelateQuestionToCategoryDto relateQuestionToCategoryDto);
}
