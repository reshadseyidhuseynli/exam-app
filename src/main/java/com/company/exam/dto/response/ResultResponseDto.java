package com.company.exam.dto.response;

import lombok.Data;

@Data
public class ResultResponseDto {

    private Integer correctAnswerCount;
    private Integer wrongAnswerCount;
    private Integer nonAnsweredCount;


    public static ResultResponseDto of(Integer correctAnswerCount,
                                       Integer wrongAnswerCount,
                                       Integer nonAnsweredCount) {
        ResultResponseDto resultResponseDto = new ResultResponseDto();
        resultResponseDto.setCorrectAnswerCount(correctAnswerCount);
        resultResponseDto.setWrongAnswerCount(wrongAnswerCount);
        resultResponseDto.setNonAnsweredCount(nonAnsweredCount);
        return resultResponseDto;
    }

}
