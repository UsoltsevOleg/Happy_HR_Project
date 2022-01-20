package com.example.happy.hr.services;

import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.domain.entities.User;
import com.example.happy.hr.json.dto.UserDto;
import com.example.happy.hr.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setSurname("Иванов");
        user.setName("Иван");
        user.setPatronymic("Иванович");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteById(1);
    }

    @Test
    void getFirstUser() {
        UserDto userDto = userService.getFirstUser();
        assertEquals(1, userDto.getId());
        assertEquals("Иванов", userDto.getSurname());
        assertEquals("Иван", userDto.getName());
        assertEquals("Иванович", userDto.getPatronymic());
    }
}