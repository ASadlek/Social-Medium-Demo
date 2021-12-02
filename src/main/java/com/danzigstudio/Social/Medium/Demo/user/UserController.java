package com.danzigstudio.Social.Medium.Demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.danzigstudio.Social.Medium.Demo.user.UserMapper.userDTOToUser;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTOToUser(userDTO));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        return userService.userById(id);
    }
    }
