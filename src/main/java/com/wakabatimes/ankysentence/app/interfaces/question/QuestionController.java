package com.wakabatimes.ankysentence.app.interfaces.question;

import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;
import com.wakabatimes.ankysentence.app.domain.model.question.*;
import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.service.question.QuestionService;
import com.wakabatimes.ankysentence.app.interfaces.question.dto.QuestionResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.question.form.QuestionDeleteForm;
import com.wakabatimes.ankysentence.app.interfaces.question.form.QuestionSaveForm;
import com.wakabatimes.ankysentence.app.interfaces.question.form.QuestionUpdateForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

import static com.wakabatimes.ankysentence.config.ApiUrlConstants.API_URL;

@Slf4j
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    private HttpStatus status = HttpStatus.OK;

    @PostMapping(value = API_URL + "/questions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public QuestionResponseDto save(@Valid @RequestBody QuestionSaveForm form, Principal principal) {
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage("不正なアクセスです。");
            return questionResponseDto;
        }

        try {
            CategoryId categoryId = new CategoryId(form.getCategoryId());
            QuestionTitle questionTitle = new QuestionTitle(form.getTitle());
            QuestionAnswer questionAnswer = new QuestionAnswer(form.getAnswer());
            Question question = QuestionFactory.create(questionTitle, questionAnswer);
            questionService.save(question, categoryId);
            questionResponseDto.setId(question.getQuestionId().getValue());
            questionResponseDto.setTitle(question.getQuestionTitle().getValue());
            questionResponseDto.setAnswer(question.getQuestionAnswer().getValue());
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage("問題を作成しました。");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage(e.getMessage());
        }
        return questionResponseDto;
    }

    @PostMapping(value = API_URL + "/questions/{questionId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public QuestionResponseDto update(@PathVariable String questionId, @Valid @RequestBody QuestionUpdateForm form, Principal principal) {
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage("不正なアクセスです。");
            return questionResponseDto;
        }

        try {
            QuestionId questionId1 = new QuestionId(questionId);
            Question getQuestion = questionService.get(questionId1);
            QuestionTitle questionTitle = new QuestionTitle(form.getTitle());
            QuestionAnswer questionAnswer = new QuestionAnswer(form.getAnswer());
            Question update = new Question(questionId1,questionTitle,questionAnswer,getQuestion.getQuestionSortNumber());
            questionService.update(update);

            questionResponseDto.setId(update.getQuestionId().getValue());
            questionResponseDto.setTitle(update.getQuestionTitle().getValue());
            questionResponseDto.setAnswer(update.getQuestionAnswer().getValue());
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage("問題を更新しました。");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage(e.getMessage());
        }
        return questionResponseDto;
    }

    @PostMapping(value = API_URL + "/questions/{questionId}/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public QuestionResponseDto delete(@PathVariable String questionId, @Valid @RequestBody QuestionDeleteForm form, Principal principal) {
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage("不正なアクセスです。");
            return questionResponseDto;
        }

        try {
            QuestionId questionId1 = new QuestionId(questionId);
            questionService.delete(questionId1);
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage("問題を削除しました。");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            questionResponseDto.setStatus(status.getReasonPhrase());
            questionResponseDto.setMessage(e.getMessage());
        }
        return questionResponseDto;
    }
}
