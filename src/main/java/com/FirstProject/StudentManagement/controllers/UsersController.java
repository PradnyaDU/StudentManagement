package com.FirstProject.StudentManagement.controllers;

import com.FirstProject.StudentManagement.dto.UsersDto;
import com.FirstProject.StudentManagement.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping()
    public List<UsersDto> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UsersDto getUserById(@PathVariable int id) {
        return usersService.getUserById(id);
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @RequestBody UsersDto usersDto) {
        return usersService.createUser(usersDto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUserById(@PathVariable int id) {
        return usersService.deleteUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public UsersDto updateUserById(@PathVariable int id, @Valid @RequestBody UsersDto usersDto) {
        return usersService.updateUserById(id, usersDto);
    }
}
