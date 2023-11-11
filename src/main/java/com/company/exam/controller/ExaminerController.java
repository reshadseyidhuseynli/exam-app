package com.company.exam.controller;

import com.company.exam.dto.request.AnswerRequestDto;
import com.company.exam.dto.request.CreateExaminerRequestDto;
import com.company.exam.dto.request.EnterRequestDto;
import com.company.exam.dto.response.QuestionResponseDto;
import com.company.exam.dto.response.ResultResponseDto;
import com.company.exam.service.ExaminerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("exams")
@RequiredArgsConstructor
public class ExaminerController {

    private final ExaminerService examinerService;

    @PostMapping("register")
    public void add(@RequestBody CreateExaminerRequestDto requestDto) {
        examinerService.add(requestDto);
    }

    @PostMapping("/enter")
    public void enter(@RequestBody EnterRequestDto requestDto) {
        examinerService.enter(requestDto);
    }

    @PostMapping("/{title}/start")
    public QuestionResponseDto start(@PathVariable("title") String title,
                                     @RequestBody EnterRequestDto requestDto) {
        return examinerService.startExam(title, requestDto);
    }

    @PostMapping("/finish")
    public ResultResponseDto finish(@RequestBody AnswerRequestDto requestDto) {
        return examinerService.finishExam(requestDto);
    }

}
