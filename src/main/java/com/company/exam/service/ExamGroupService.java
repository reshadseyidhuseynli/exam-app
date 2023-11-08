package com.company.exam.service;

import com.company.exam.entity.ExamGroup;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.repository.ExamGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamGroupService {

    private final ExamGroupRepository examGroupRepository;

    public ExamGroup findByNameOrAddIfNotExist(String groupName) {
        if (examGroupRepository.existsByName(groupName)){
            return examGroupRepository.findByName(groupName)
                    .orElseThrow(() -> new NotFoundException("not found exam group like: " + groupName));
        } else {
            ExamGroup examGroup = new ExamGroup();
            examGroup.setName(groupName);
            return examGroupRepository.save(examGroup);
        }
    }
}
