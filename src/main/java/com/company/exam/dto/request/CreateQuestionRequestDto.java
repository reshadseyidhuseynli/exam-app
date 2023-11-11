package com.company.exam.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreateQuestionRequestDto {

    @NotBlank
    private String title;

    @NotEmpty
    private List<Option> options;

    @Data
    public static class Option {

        private String optionText;
        private Boolean isRightAnswer;

    }

}
