package com.wakabatimes.ankysentence.app.infrastructure.question;

import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;
import com.wakabatimes.ankysentence.app.domain.model.question.*;
import com.wakabatimes.ankysentence.app.infrastructure.question.dto.QuestionDto;
import com.wakabatimes.ankysentence.app.infrastructure.question.dto.RelateQuestionToCategoryDto;
import com.wakabatimes.ankysentence.app.infrastructure.question.mapper.QuestionMapper;
import com.wakabatimes.ankysentence.app.infrastructure.question.mapper.RelateQuestionToCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private RelateQuestionToCategoryMapper relateQuestionToCategoryMapper;

    @Override
    public void save(Question question, CategoryId categoryId) {
        RelateQuestionToCategoryDto relateQuestionToCategoryDto = new RelateQuestionToCategoryDto(question,categoryId);
        List<QuestionDto> questionDtos = questionMapper.list(relateQuestionToCategoryDto);
        Questions questions = new Questions();
        int i = 0;
        for(QuestionDto questionDto : questionDtos) {
            QuestionId questionId = new QuestionId(questionDto.getId());
            QuestionTitle questionTitle = new QuestionTitle(questionDto.getTitle());
            QuestionAnswer questionAnswer = new QuestionAnswer(questionDto.getAnswer());
            QuestionSortNumber questionSortNumber = new QuestionSortNumber(questionDto.getSortNumber());
            Question question1 = new Question(questionId,questionTitle,questionAnswer,questionSortNumber);
            questions.add(question1);
            i++;
        }

        QuestionDto questionDto = new QuestionDto(question);
        questionDto.setSortNumber(i + 1);
        questionMapper.save(questionDto);
        relateQuestionToCategoryMapper.save(relateQuestionToCategoryDto);
    }

    @Override
    public Questions list(CategoryId categoryId) {
        RelateQuestionToCategoryDto relateQuestionToCategoryDto = new RelateQuestionToCategoryDto();
        relateQuestionToCategoryDto.setCategoryId(categoryId.getValue());
        List<QuestionDto> questionDtos = questionMapper.list(relateQuestionToCategoryDto);
        Questions questions = new Questions();
        for(QuestionDto questionDto : questionDtos) {
            QuestionId questionId = new QuestionId(questionDto.getId());
            QuestionTitle questionTitle = new QuestionTitle(questionDto.getTitle());
            QuestionAnswer questionAnswer = new QuestionAnswer(questionDto.getAnswer());
            QuestionSortNumber questionSortNumber = new QuestionSortNumber(questionDto.getSortNumber());
            Question question = new Question(questionId,questionTitle,questionAnswer,questionSortNumber);
            questions.add(question);
        }
        return questions;
    }

    @Override
    public Question get(QuestionId questionId) {
        RelateQuestionToCategoryDto relateQuestionToCategoryDto = new RelateQuestionToCategoryDto();
        relateQuestionToCategoryDto.setQuestionId(questionId.getValue());
        QuestionDto result = questionMapper.get(relateQuestionToCategoryDto);
        if(result != null) {
            QuestionId questionId2 = new QuestionId(result.getId());
            QuestionTitle questionTitle = new QuestionTitle(result.getTitle());
            QuestionAnswer questionAnswer = new QuestionAnswer(result.getAnswer());
            QuestionSortNumber questionSortNumber = new QuestionSortNumber(result.getSortNumber());
            return new Question(questionId2,questionTitle,questionAnswer,questionSortNumber);
        }else {
            throw new RuntimeException("指定されたIDが見つかりません");
        }

    }

    @Override
    public void update(Question question) {
        QuestionDto questionDto = new QuestionDto(question);
        questionDto.setSortNumber(question.getQuestionSortNumber().getValue());
        questionMapper.update(questionDto);
    }

    @Override
    public void delete(QuestionId questionId) {
        RelateQuestionToCategoryDto relateQuestionToCategoryDto = new RelateQuestionToCategoryDto();
        relateQuestionToCategoryDto.setQuestionId(questionId.getValue());
        questionMapper.delete(relateQuestionToCategoryDto);
    }
}
