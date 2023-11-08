package com.company.exam.service;

import com.company.exam.dto.OptionInfo;
import com.company.exam.dto.QuestionInfo;
import com.company.exam.entity.Exam;
import com.company.exam.entity.Question;
import com.company.exam.entity.QuestionOption;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.repository.ExamRepository;
import com.company.exam.repository.QuestionOptionRepository;
import com.company.exam.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final ExamRepository examRepository;

    public void addQuestion(QuestionInfo request) {
        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new NotFoundException("Not found exam by id: " + request.getExamId()));

        Question question = Question.builder()
                .exam(exam)
                .title(request.getTitle())
                .build();
        Question savedQuestion = questionRepository.save(question);

        request.getOptions().forEach(optionInfo -> {
            QuestionOption questionOption = QuestionOption.builder()
                    .question(savedQuestion)
                    .optionText(optionInfo.getOptionText())
                    .isRightAnswer(optionInfo.isRightAnswer())
                    .build();
            questionOptionRepository.save(questionOption);
        });
    }

    public void updateQuestion(Integer id, QuestionInfo request) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found question by id: " + id));
        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new NotFoundException("Not found exam by id: " + request.getExamId()));

        question.setExam(exam);
        question.setTitle(request.getTitle());

        questionRepository.save(question);

        List<QuestionOption> options = questionOptionRepository.findByQuestion(question)
                .orElseThrow(() -> new NotFoundException("Not found question option by question id: " + question.getId()));
        List<OptionInfo> optionInfos = request.getOptions();

        for (int i = 0; i <= options.size()-1; i++) {
            QuestionOption questionOption = options.get(i);
            OptionInfo optionInfo = optionInfos.get(i);

            questionOption.setOptionText(optionInfo.getOptionText());
            questionOption.setRightAnswer(optionInfo.isRightAnswer());

            questionOptionRepository.save(questionOption);
        }
    }

    public QuestionInfo getQuestionById(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found question by id: " + id));
        List<QuestionOption> questionOptions = questionOptionRepository.findByQuestion(question)
                .orElseThrow(() -> new NotFoundException("Not found question option by question id: " + question.getId()));

        List<OptionInfo> optionInfos = questionOptions.stream()
                .map(questionOption -> {
                    OptionInfo optionInfo = new OptionInfo();
                    optionInfo.setOptionText(questionOption.getOptionText());
                    optionInfo.setRightAnswer(questionOption.isRightAnswer());
                    return optionInfo;
                })
                .collect(Collectors.toList());

        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setExamId(question.getExam().getId());
        questionInfo.setTitle(question.getTitle());
        questionInfo.setOptions(optionInfos);

        return questionInfo;
    }

    public void deleteQuestion(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found question by id: " + id));
        List<QuestionOption> questionOptions = questionOptionRepository.findByQuestion(question)
                .orElseThrow(() -> new NotFoundException("Not found question option by question id: " + question.getId()));
        questionOptionRepository.deleteAll(questionOptions);
        questionRepository.deleteById(id);
    }
}