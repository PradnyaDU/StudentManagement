package com.FirstProject.StudentManagement.repository;

import com.FirstProject.StudentManagement.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity, Integer> {
}
