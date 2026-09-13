package com.FirstProject.StudentManagement.repository;

import com.FirstProject.StudentManagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    List<Users> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
