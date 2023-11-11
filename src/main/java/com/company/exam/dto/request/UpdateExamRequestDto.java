package com.company.exam.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class UpdateExamRequestDto {

    private String groupName;
    private String title;

    @Min(value = 1, message = "Invalid exam duration")
    private String duration;

    @Min(value = 1, message = "Invalid exam duration")
    private Integer questionCount;

}
