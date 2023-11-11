package com.company.exam.repository;

import com.company.exam.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Long> {

    Optional<ResultEntity> findByEntranceCode(String entranceCode);
}
