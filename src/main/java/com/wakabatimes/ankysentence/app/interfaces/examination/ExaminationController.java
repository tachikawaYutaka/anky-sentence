package com.wakabatimes.ankysentence.app.interfaces.examination;

import com.wakabatimes.ankysentence.app.domain.aggregates.examination.Examination;
import com.wakabatimes.ankysentence.app.domain.aggregates.examination.ExaminationInput;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;
import com.wakabatimes.ankysentence.app.domain.model.question.QuestionId;
import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.service.examination.ExaminationService;
import com.wakabatimes.ankysentence.app.interfaces.examination.dto.ExaminationResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.examination.form.ExaminationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

import static com.wakabatimes.ankysentence.config.ApiUrlConstants.API_URL;

@Slf4j
@RestController
public class ExaminationController {
    @Autowired
    private ExaminationService examinationService;

    private HttpStatus status = HttpStatus.OK;

    @PostMapping(value = API_URL + "/examination", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExaminationResponseDto calculate(Principal principal, @Valid @RequestBody ExaminationForm form){
        ExaminationResponseDto examinationResponseDto = new ExaminationResponseDto();
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            examinationResponseDto.setStatus(status.getReasonPhrase());
            examinationResponseDto.setMessage("不正なアクセスです。");
            return examinationResponseDto;
        }

        try {
            QuestionId questionId = new QuestionId(form.getQuestionId());
            ExaminationInput examinationInput = new ExaminationInput(form.getInput());
            Examination examination = new Examination(questionId,examinationInput);
            AnswerResult answerResult = examinationService.calculateConcordance(examination);
            examinationResponseDto.setResultId(answerResult.getAnswerResultId().getValue());
            examinationResponseDto.setScore(answerResult.getAnswerResultScore().getValue());
            examinationResponseDto.setDate(answerResult.getAnswerResultDate().getValue());
            examinationResponseDto.setStatus(status.getReasonPhrase());
            examinationResponseDto.setMessage("採点しました。");
        } catch(RuntimeException e){
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            examinationResponseDto.setStatus(status.getReasonPhrase());
            examinationResponseDto.setMessage(e.getMessage());
        }
        return examinationResponseDto;
    }
}
