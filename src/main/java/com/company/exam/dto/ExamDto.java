package com.company.exam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ExamDto {

    private String groupName;
    private String title;

    @JsonProperty("detail")
    private ExamDetailDto examDetailDto;

    @Data
    public static class ExamDetailDto{

        @Min(value = 1, message = "Invalid exam duration")
        private String duration;

        @Min(value = 1, message = "Invalid exam duration")
        private Integer questionCount;

    }
}
