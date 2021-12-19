package com.danzigstudio.Social.Medium.Demo.user;

import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.danzigstudio.Social.Medium.Demo.profile.ProfileMapper.createProfile;
import static com.danzigstudio.Social.Medium.Demo.user.UserMapper.userDTOToUser;
import static com.danzigstudio.Social.Medium.Demo.user.UserMapper.userToUserDTO;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @Autowired
    public UserController(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.addUser(userDTOToUser(userDTO));
        profileService.addProfile(createProfile(user));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO getUserById(@PathVariable("id") Long id) {
        User user = userService.userById(id).get();
        return userToUserDTO(user);
    }



}
