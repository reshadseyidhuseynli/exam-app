package com.company.exam.mapper;

import com.company.exam.dto.QuestionDto;
import com.company.exam.dto.request.CreateQuestionRequestDto;
import com.company.exam.entity.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {

    QuestionEntity toQuestionEntity(CreateQuestionRequestDto requestDto);

    QuestionDto toQuestionDto(QuestionEntity questionEntity);

}
