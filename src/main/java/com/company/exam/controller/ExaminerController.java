package com.company.exam.controller;

import com.company.exam.dto.request.CreateExaminerRequestDto;
import com.company.exam.dto.request.EnterRequestDto;
import com.company.exam.service.ExaminerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/examiners")
@RequiredArgsConstructor
public class ExaminerController {

    private final ExaminerService examinerService;

    @PostMapping("/register")
    public void add(@RequestBody CreateExaminerRequestDto requestDto) {
        examinerService.add(requestDto);
    }

    @PostMapping("/entry")
    public void enter(@RequestBody EnterRequestDto requestDto) {
        examinerService.enter(requestDto);
    }


}
