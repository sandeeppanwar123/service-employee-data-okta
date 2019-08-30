package com.daffodil.repository;


import com.daffodil.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {


    Optional<EmployeeEntity> findByEmployeeId(Integer s);

    void deleteByEmployeeId(Integer integer);


    boolean existsByEmployeeId(Integer integer);
}
