package com.company.exam.mapper;

import com.company.exam.dto.ExaminerInfo;
import com.company.exam.entity.Examiner;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExaminerMapper {

    ExaminerInfo toDto(Examiner examiner);
}
