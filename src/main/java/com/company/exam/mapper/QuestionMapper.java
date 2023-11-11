package com.company.exam.mapper;

import com.company.exam.dto.QuestionDto;
import com.company.exam.dto.request.CreateQuestionRequestDto;
import com.company.exam.entity.OptionEntity;
import com.company.exam.entity.QuestionEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {

    QuestionEntity toQuestionEntity(CreateQuestionRequestDto requestDto);

    @AfterMapping
    default void mapOptions(@MappingTarget QuestionEntity questionEntity,
                            CreateQuestionRequestDto requestDto) {
        int size = requestDto.getOptions().size();
        List<OptionEntity> optionEntities = new ArrayList<>(size);

        for (CreateQuestionRequestDto.Option option : requestDto.getOptions()) {
            OptionEntity optionEntity = new OptionEntity();
            optionEntity.setText(option.getOptionText());
            optionEntity.setRightAnswer(option.getIsRightAnswer());
            optionEntities.add(optionEntity);
        }

        questionEntity.setOptions(optionEntities);
    }

    QuestionDto toQuestionDto(QuestionEntity questionEntity);

    @AfterMapping
    default void mapOptions(@MappingTarget QuestionDto responseDto,
                            QuestionEntity questionEntity) {
        int size = questionEntity.getOptions().size();
        List<QuestionDto.Option> options = new ArrayList<>(size);

        for (OptionEntity optionEntity : questionEntity.getOptions()) {
            QuestionDto.Option option = new QuestionDto.Option();
            option.setNumber(optionEntity.getNumber());
            option.setText(optionEntity.getText());
            options.add(option);
        }

        responseDto.setOptions(options);
    }

}
