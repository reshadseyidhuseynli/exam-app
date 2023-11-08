package com.company.exam.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionInfo {

    private Integer examId;
    private String title;
    private List<OptionInfo> options;

}
