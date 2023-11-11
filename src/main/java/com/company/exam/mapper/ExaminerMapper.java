package com.company.exam.mapper;

import com.company.exam.dto.request.CreateExaminerRequestDto;
import com.company.exam.entity.ExaminerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExaminerMapper {

    ExaminerEntity toExaminerEntity(CreateExaminerRequestDto requestDto);
}
