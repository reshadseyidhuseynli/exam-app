package com.company.exam.mapper;

import com.company.exam.dto.ExaminerResultInfo;
import com.company.exam.entity.ExaminerResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExaminerResultMapper {

    @Mapping(target = "examName", expression = "java(examinerResult.getExam().getExamDetails().getExamName())")
    ExaminerResultInfo toDto(ExaminerResult examinerResult);

    List<ExaminerResultInfo> toDtoList(List<ExaminerResult> examinerResults);
}
