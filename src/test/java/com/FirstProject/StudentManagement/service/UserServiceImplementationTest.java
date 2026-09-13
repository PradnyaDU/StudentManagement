package com.FirstProject.StudentManagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplementationTest {
    @Autowired
    UsersServiceImplementations usersServiceImplementations;
    @Test
    public void testCreateUser() {
        String username = usersServiceImplementations.getUserById(11).getUserName();

        assertEquals("Harshal", username);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3",
            "1, 4",
            "0, 5"
    })
    public void test(int a, int b) {

        assertEquals(5, a+b,"Sum of " + a + " and " + b + " should be 5");
    }

}
