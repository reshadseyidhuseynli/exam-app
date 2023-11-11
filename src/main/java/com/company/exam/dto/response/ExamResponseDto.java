package com.company.exam.dto.response;

import com.company.exam.dto.ExamDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ExamResponseDto {

    @JsonProperty("exams")
    private List<ExamDto> examDtoList;

    public static ExamResponseDto of(List<ExamDto> examDtoList) {
        ExamResponseDto examResponseDto = new ExamResponseDto();
        examResponseDto.setExamDtoList(examDtoList);
        return examResponseDto;
    }

}
