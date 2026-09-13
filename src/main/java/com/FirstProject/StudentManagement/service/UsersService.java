package com.FirstProject.StudentManagement.service;

import com.FirstProject.StudentManagement.dto.UsersDto;
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
