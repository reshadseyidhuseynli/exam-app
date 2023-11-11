package com.company.exam.controller;

import com.company.exam.dto.request.CreateQuestionRequestDto;
import com.company.exam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public void add(@RequestParam("examTitle") String examTitle,
                            @Valid @RequestBody CreateQuestionRequestDto requestDto) {
        questionService.add(examTitle, requestDto);
    }

}
