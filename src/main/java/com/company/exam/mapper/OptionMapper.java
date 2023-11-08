package com.company.exam.mapper;

import com.company.exam.dto.OptionInfo;
import com.company.exam.entity.QuestionOption;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OptionMapper {

    QuestionOption toEntity(OptionInfo dto);

}
