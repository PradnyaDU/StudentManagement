package com.FirstProject.StudentManagement.service;

import com.FirstProject.StudentManagement.dto.UsersDto;
import com.FirstProject.StudentManagement.entity.Users;
import com.FirstProject.StudentManagement.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImplementations implements UsersService {

    private final UsersRepo usersRepo;
    private final BCryptPasswordEncoder passwordEncoder;
//    private final static Logger logger= LoggerFactory.getLogger(UsersServiceImplementations.class);

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> users = usersRepo.findAll();

        return users.stream().map(user -> new UsersDto(user.getUserid(), user.getUserName(), user.getUserPassword(), user.getRole(), Collections.singletonList(user.getAuthorities()))).toList();
    }

    @Override
    public UsersDto getUserById(int id) {
        Users users = usersRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exists with id " + id));
        return new UsersDto(users.getUserid(), users.getUserName(), users.getUserPassword(), users.getRole(), Collections.singletonList(users.getAuthorities()));
    }

    @Override
    public String createUser(UsersDto usersDto) {
        if (usersRepo.existsByUserName(usersDto.getUserName())) {
//            logger.info("this is my custom error: Username already exists");
            log.trace("Trace");
            log.debug("debug");
            log.info("info");
            log.warn("warn");
            log.error("error");

            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        Users users = new Users();
        users.setUserName(usersDto.getUserName());
        users.setUserPassword(passwordEncoder.encode(usersDto.getUserPassword()));
        users.setRole(usersDto.getRole().toUpperCase());
        users.setAuthorities(
                usersDto.getAuthorities()
                        .stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.joining(","))
        );
        Users savedUser = usersRepo.save(users);

        return "User created successfully with id: " + savedUser.getUserid();
    }

    @Override
    public boolean deleteUserById(int id) {
        Users userEntityToDelete = usersRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exists with id " + id));
        usersRepo.delete(userEntityToDelete);
        return true;
    }

    @Override
    public UsersDto updateUserById(int id, UsersDto usersDto) {
        Users users = usersRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student does not exists with id " + id));
        users.setUserName(usersDto.getUserName());
        users.setUserPassword(passwordEncoder.encode(usersDto.getUserPassword()));
        users.setRole(usersDto.getRole().toUpperCase());
        users.setAuthorities(
                usersDto.getAuthorities()
                        .stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.joining(","))
        );
        Users savedUser = usersRepo.save(users);
        return new UsersDto(savedUser.getUserid(), savedUser.getUserName(), savedUser.getUserPassword(), savedUser.getRole(), Collections.singletonList(savedUser.getAuthorities()));
    }
}
