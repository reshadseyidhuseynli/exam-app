package com.company.exam.controller;

import com.company.exam.dto.ExamDto;
import com.company.exam.dto.request.CreateExamRequestDto;
import com.company.exam.dto.request.UpdateExamRequestDto;
import com.company.exam.dto.response.ExamResponseDto;
import com.company.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exams")
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

}
