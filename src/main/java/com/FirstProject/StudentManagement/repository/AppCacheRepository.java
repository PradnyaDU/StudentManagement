package com.FirstProject.StudentManagement.repository;

import com.FirstProject.StudentManagement.dto.AppCacheDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCacheRepository extends JpaRepository<AppCacheDTO, Integer> {

    AppCacheDTO findByConfigKey(String configKey);
}
