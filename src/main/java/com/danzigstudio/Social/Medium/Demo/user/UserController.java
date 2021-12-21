package com.danzigstudio.Social.Medium.Demo.user;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
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

    @PostMapping("/{id}/update/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePassword(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        User user = userService.userById(id).get();
        user.setPassword(userDTO.getPassword());
        userService.addUser(user);
    }

    @PostMapping("/{id}/update/first_name")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateFirstName(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        User user = userService.userById(id).get();
        user.setFirstName(userDTO.getFirstName());
        userService.addUser(user);
        Profile profile = user.getProfile();
        profile.setFirstName(userDTO.getFirstName());
        profileService.addProfile(profile);
    }

    @PostMapping("/{id}/update/last_name")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateLastName(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        User user = userService.userById(id).get();
        user.setLastName(userDTO.getLastName());
        userService.addUser(user);
        Profile profile = user.getProfile();
        profile.setLastName(userDTO.getLastName());
        profileService.addProfile(profile);
    }

    @PostMapping("/{id}/update/email")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateEmail(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        User user = userService.userById(id).get();
        user.setEmail(userDTO.getEmail());
        userService.addUser(user);
    }


}
