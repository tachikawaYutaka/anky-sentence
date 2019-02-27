package com.wakabatimes.ankysentence.app.domain.service.examination;

import com.wakabatimes.ankysentence.app.domain.aggregates.examination.Examination;
import com.wakabatimes.ankysentence.app.domain.model.answer_result.AnswerResult;

public interface ExaminationService {
    /**
     * 入力された回答の一致率を返す
     * @param examination
     * @return
     */
    AnswerResult calculateConcordance(Examination examination);
}
