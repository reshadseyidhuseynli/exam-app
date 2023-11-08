package com.company.exam.service;

import com.company.exam.dto.OptionInfo;
import com.company.exam.dto.QuestionInfo;
import com.company.exam.dto.QuestionRequest;
import com.company.exam.entity.Exam;
import com.company.exam.entity.Question;
import com.company.exam.entity.QuestionOption;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.mapper.OptionMapper;
import com.company.exam.mapper.QuestionMapper;
import com.company.exam.repository.ExamRepository;
import com.company.exam.repository.QuestionOptionRepository;
import com.company.exam.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final QuestionOptionRepository questionOptionRepository;
    private final OptionMapper optionMapper;
    private final ExamRepository examRepository;

    public void addQuestion(QuestionRequest request) {
        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new NotFoundException("Not found exam by id: " + request.getExamId()));

        Question question = Question.builder()
                .exam(exam)
                .title(request.getTitle())
                .build();
        Question savedQuestion = questionRepository.save(question);

        List<OptionInfo> optionInfos = request.getOptions();
        optionInfos.forEach(optionInfo -> {
                    QuestionOption questionOption = optionMapper.toEntity(optionInfo);
                    questionOption.setQuestion(savedQuestion);
                    questionOptionRepository.save(questionOption);
                });
    }

    public void updateQuestion(Integer id, QuestionRequest request) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found question by id: " + id));
        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new NotFoundException("Not found exam by id: " + request.getExamId()));

        question.setExam(exam);
        question.setTitle(request.getTitle());

        questionRepository.save(question);

        List<QuestionOption> options = question.getOptions();
        List<OptionInfo> optionInfos = request.getOptions();

        for (int i = 0; i <= options.size() - 1; i++) {
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
        return questionMapper.toDto(question);
    }

    public List<QuestionInfo> getQuestionsByExamId(Integer examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new NotFoundException("Not found exam by id: " + examId));
        List<Question> questions = exam.getQuestions();
        return questionMapper.toDtoList(questions);
    }

    public void deleteQuestion(Integer id) {
        List<QuestionOption> questionOptions = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found question by id: " + id))
                .getOptions();
        questionOptionRepository.deleteAll(questionOptions);
        questionRepository.deleteById(id);
    }
}
