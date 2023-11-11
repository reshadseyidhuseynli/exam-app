package com.company.exam.mapper;

import com.company.exam.dto.ExamDto;
import com.company.exam.dto.request.CreateExamRequestDto;
import com.company.exam.dto.request.UpdateExamRequestDto;
import com.company.exam.entity.ExamDetailEntity;
import com.company.exam.entity.ExamEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExamMapper {

    @Mapping(target = "groupName", source = "examDetail.group.name")
    @Mapping(target = "examDetailDto.duration", source = "examDetail.duration")
    @Mapping(target = "examDetailDto.questionCount", source = "examDetail.questionCount")
    ExamDto toExamDto(ExamEntity examEntity);

    @Mapping(target = "examDetail.group.name", source = "groupName")
    @Mapping(target = "examDetail.duration", source = "duration")
    @Mapping(target = "examDetail.questionCount", source = "questionCount")
    ExamEntity toExamEntity(CreateExamRequestDto requestDto);

    void updateExamEntity(@MappingTarget ExamEntity examEntity, UpdateExamRequestDto requestDto);

    @AfterMapping
    default void mapUpdatedExamDetails(@MappingTarget ExamEntity examEntity,
                                       UpdateExamRequestDto updateExamRequestDto) {
        ExamDetailEntity examDetail = examEntity.getExamDetail();

        examDetail.getGroup().setName(updateExamRequestDto.getGroupName());
        examDetail.setDuration(updateExamRequestDto.getDuration());
        examDetail.setQuestionCount(updateExamRequestDto.getQuestionCount());
    }
}
