package com.FirstProject.StudentManagement.Service;

import com.FirstProject.StudentManagement.DTO.UsersDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
  
    List<UsersDto> getAllUsers();

    UsersDto getUserById(int id);

    String createUser(@Valid UsersDto usersDto);

    boolean deleteUserById(int id);

    UsersDto updateUserById(int id, @Valid UsersDto usersDto);
}
