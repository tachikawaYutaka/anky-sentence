package com.wakabatimes.ankysentence.app.interfaces.examination.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExaminationResponseDto {
    private String resultId;
    private Integer score;
    private Date date;
    private String status;
    private String message;

}
