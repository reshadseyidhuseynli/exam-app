package com.company.exam.controller;

import com.company.exam.dto.ExamDto;
import com.company.exam.dto.request.AnswerRequestDto;
import com.company.exam.dto.request.CreateExamRequestDto;
import com.company.exam.dto.request.EnterRequestDto;
import com.company.exam.dto.request.UpdateExamRequestDto;
import com.company.exam.dto.response.ExamResponseDto;
import com.company.exam.dto.response.QuestionResponseDto;
import com.company.exam.dto.response.ResultResponseDto;
import com.company.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping("/{title}")
    public ExamDto getByTitle(@PathVariable("title") String title) {
        return examService.getByTitle(title);
    }

    @GetMapping()
    public ExamResponseDto getAll() {
        return examService.getAll();
    }

    @PostMapping
    public void addExam(@Valid @RequestBody CreateExamRequestDto requestDto) {
        examService.addExam(requestDto);
    }

    @PutMapping("/{title}")
    public void updateExam(@PathVariable String title,
                           @Valid @RequestBody UpdateExamRequestDto requestDto) {
        examService.updateExam(title, requestDto);
    }

    @DeleteMapping("/{title}")
    public void deleteByTitle(@PathVariable String title) {
        examService.deleteByTitle(title);
    }

    @PostMapping("/{title}/start")
    public QuestionResponseDto start(@PathVariable("title") String title,
                                     @RequestBody EnterRequestDto requestDto) {
        return examService.startExam(title, requestDto);
    }

    @PostMapping("/finish")
    public ResultResponseDto finish(@RequestBody AnswerRequestDto requestDto) {
        return examService.finishExam(requestDto);
    }

}
