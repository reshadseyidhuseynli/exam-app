package com.company.exam.dto.request;

import lombok.Data;

@Data
public class NewExamDetails {

    private String groupName;
    private String examName;
    private String duration;
    private Integer questionCount;

}
