package com.company.exam.dto.response;

import com.company.exam.dto.QuestionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponseDto {

    @JsonProperty("questions")
    private List<QuestionDto> questionDtoList;

    public static QuestionResponseDto of(List<QuestionDto> questionDtoList) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setQuestionDtoList(questionDtoList);
        return questionResponseDto;
    }

}
