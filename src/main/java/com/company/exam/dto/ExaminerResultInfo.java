package com.company.exam.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExaminerResultInfo {

    private String examName;
    private String entranceCode;
    private Integer correctAnswerCount;
    private Integer wrongAnswerCount;
    private Integer nonAnswerCount;
    private LocalDateTime examStartTime;
    private LocalDateTime examEndTime;
}
