package com.company.exam.controller;

import com.company.exam.dto.ExamDetailsInfo;
import com.company.exam.dto.QuestionInfo;
import com.company.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public void addExam(@RequestBody ExamDetailsInfo examDetails) {
        examService.addExam(examDetails);
    }

    @GetMapping("/{id}/details")
    public ExamDetailsInfo getById(@PathVariable Integer id) {
        return examService.getById(id);
    }

    @GetMapping("/{id}/questions")
    public List<QuestionInfo> getQuestionsByExamId(@PathVariable Integer id) {
        return examService.getQuestionsByExamId(id);
    }

    @PutMapping("/{id}")
    public void updateExam(@PathVariable Integer id,
                           @RequestBody ExamDetailsInfo examDetails) {
        examService.updateExam(id, examDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        examService.deleteById(id);
    }
}
