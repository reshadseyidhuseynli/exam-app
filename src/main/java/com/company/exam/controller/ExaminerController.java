package com.company.exam.controller;

import com.company.exam.dto.ExaminerInfo;
import com.company.exam.dto.ExaminerResultInfo;
import com.company.exam.service.ExaminerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("examiner")
@RequiredArgsConstructor
public class ExaminerController {

    private final ExaminerService examinerService;

    @GetMapping("/{pin}")
    public ExaminerInfo getExaminerByPin(@PathVariable String pin) {
        return examinerService.getExaminerByPin(pin);
    }

    @GetMapping("/{pin}/results")
    public List<ExaminerResultInfo> getExaminerResultsByPin(@PathVariable String pin) {
        return examinerService.getExaminerResultsByPin(pin);
    }

    @GetMapping("/result/{id}")
    public ExaminerResultInfo getExaminerResultsByPin(@PathVariable Integer id) {
        return examinerService.getExaminerResultById(id);
    }
}
