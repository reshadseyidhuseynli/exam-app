package com.company.exam.controller;

import com.company.exam.dto.QuestionInfo;
import com.company.exam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public void addQuestion(@RequestBody QuestionInfo question) {
        questionService.addQuestion(question);
    }

    @PutMapping("/{id}")
    public void updateQuestion(@PathVariable Integer id,
                               @RequestBody QuestionInfo question) {
        questionService.updateQuestion(id, question);
    }

    @GetMapping("/{id}")
    public QuestionInfo getQuestionById(@PathVariable Integer id) {
        return questionService.getQuestionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
