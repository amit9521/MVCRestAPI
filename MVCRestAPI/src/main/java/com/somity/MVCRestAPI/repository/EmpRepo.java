package com.somity.MVCRestAPI.repository;

import com.somity.MVCRestAPI.entity.EmpEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<EmpEntities, Long> {
}
