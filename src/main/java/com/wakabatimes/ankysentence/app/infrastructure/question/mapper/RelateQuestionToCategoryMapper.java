package com.wakabatimes.ankysentence.app.infrastructure.question.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.question.dto.RelateQuestionToCategoryDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelateQuestionToCategoryMapper {
    @Insert("INSERT \n" +
            "INTO relate_question_to_category(question_id, category_id) \n" +
            "VALUES (#{questionId}, #{categoryId})")
    void save(RelateQuestionToCategoryDto relateQuestionToCategoryDto);
}
