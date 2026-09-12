package com.FirstProject.StudentManagement.Repository;

import com.FirstProject.StudentManagement.DTO.AppCacheDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCacheRepository extends JpaRepository<AppCacheDTO, Integer> {

    AppCacheDTO findByConfigKey(String configKey);
}
