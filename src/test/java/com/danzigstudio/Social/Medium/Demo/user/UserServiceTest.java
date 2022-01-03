package com.danzigstudio.Social.Medium.Demo.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void userServiceTestBefore() {
        user = new User.Builder().firstName("Jan").lastName("Kowalski").build();
    }

    @Test
    void checkNames_NameContainsOnlyLetters_True() {
        assertEquals(true, userService.checkNames(user));
    }

    @Test
    void checkNames_NameContainsNumber_False() {
        user.setFirstName(user.getFirstName() + 3);
        assertEquals(false, userService.checkNames(user));
    }

    @Test
    void checkNames_NameContainsNull_NullPointerException() {
        user.setFirstName(null);
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> userService.checkNames(user)
        );
    }

}