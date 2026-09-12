package com.FirstProject.StudentManagement.Repository;

import com.FirstProject.StudentManagement.Entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity, Integer> {
}
