package com.company.exam.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionInfo {

    private String examName;
    private String title;
    private List<OptionInfo> options;

}
