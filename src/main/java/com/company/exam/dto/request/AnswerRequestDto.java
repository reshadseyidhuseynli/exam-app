package com.company.exam.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequestDto {

    private String entranceCode;
    private List<Answer> answers;

    @Data
    public static class Answer {
        private Integer questionNumber;
        private Integer selectedOptionNumber;
    }

}
