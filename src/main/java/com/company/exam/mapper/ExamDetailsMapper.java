package com.company.exam.mapper;

import com.company.exam.dto.ExamDetailsInfo;
import com.company.exam.entity.ExamDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExamDetailsMapper {

    @Mapping(target = "groupName", expression = "java(setGroupName(examDetails.getGroup().getName()))")
    ExamDetailsInfo toDto(ExamDetails examDetails);
}
