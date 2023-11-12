package com.company.exam.service;

import com.company.exam.dto.request.CreateExaminerRequestDto;
import com.company.exam.dto.request.EnterRequestDto;
import com.company.exam.entity.ExaminerEntity;
import com.company.exam.entity.ResultEntity;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.mapper.ExaminerMapper;
import com.company.exam.repository.ExaminerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExaminerService {

    private final ExaminerRepository examinerRepository;
    private final ExaminerMapper examinerMapper;

    public void add(CreateExaminerRequestDto requestDto) {
        ExaminerEntity examinerEntity = examinerMapper.toExaminerEntity(requestDto);
        examinerRepository.save(examinerEntity);
    }

    public void enter(EnterRequestDto requestDto) {
        ExaminerEntity examinerEntity = examinerRepository.findByPin(requestDto.getPin())
                .orElseThrow(() -> new NotFoundException(
                        "Not found examiner by given pin: " + requestDto.getPin()));

        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setEntranceCode(requestDto.getEntranceCode());
        resultEntity.setExaminer(examinerEntity);

        examinerEntity.getResults().add(resultEntity);
        examinerRepository.save(examinerEntity);
    }

}
