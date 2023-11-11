package com.company.exam.dto.request;

import lombok.Data;

@Data
public class CreateExaminerRequestDto {

    private String name;
    private String surname;
    private String pin;

}
