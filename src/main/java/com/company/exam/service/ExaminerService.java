package com.company.exam.service;

import com.company.exam.dto.ExaminerInfo;
import com.company.exam.dto.ExaminerResultInfo;
import com.company.exam.entity.Examiner;
import com.company.exam.entity.ExaminerResult;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.mapper.ExaminerMapper;
import com.company.exam.mapper.ExaminerResultMapper;
import com.company.exam.repository.ExaminerRepository;
import com.company.exam.repository.ExaminerResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminerService {

    private final ExaminerRepository examinerRepository;
    private final ExaminerMapper examinerMapper;
    private final ExaminerResultRepository examinerResultRepository;
    private final ExaminerResultMapper examinerResultMapper;

    public ExaminerInfo getExaminerByPin(String pin) {
        Examiner examiner = examinerRepository.findByPin(pin)
                .orElseThrow(() -> new NotFoundException("Not found examiner by given pin: " + pin));
        return examinerMapper.toDto(examiner);
    }

    public List<ExaminerResultInfo> getExaminerResultsByPin(String pin) {
        Examiner examiner = examinerRepository.findByPin(pin)
                .orElseThrow(() -> new NotFoundException("Not found examiner by given pin: " + pin));
        return examinerResultMapper.toDtoList(examiner.getExaminerResults());
    }

    public ExaminerResultInfo getExaminerResultById(Integer id) {
        ExaminerResult examinerResult = examinerResultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found examiner result by given id: " + id));
        return examinerResultMapper.toDto(examinerResult);
    }

}
