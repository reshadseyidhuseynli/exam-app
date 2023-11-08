package com.company.exam.mapper;

import com.company.exam.dto.QuestionInfo;
import com.company.exam.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {

    @Mapping(target = "examName", expression = "java(question.getExam().getExamDetails().getExamName())")
    QuestionInfo toDto(Question question);

    List<QuestionInfo> toDtoList(List<Question> questions);
}
