package com.company.exam.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {

    private Integer number;
    private String title;
    private List<Option> options;

    @Data
    public static class Option {

        private Integer number;
        private String text;

    }
}
