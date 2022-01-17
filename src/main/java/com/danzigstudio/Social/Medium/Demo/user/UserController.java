package com.danzigstudio.Social.Medium.Demo.user;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import com.danzigstudio.Social.Medium.Demo.role.Role;
import com.danzigstudio.Social.Medium.Demo.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        User user = userDTOToUser(userDTO);
        if(userService.checkNames(user)){
            userService.addUser(user);
            profileService.addProfile(createProfile(user));
        }
        else throw new IllegalArgumentException("Only letters can be used in names");
    }

    @PostMapping("/add/role")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRole(@RequestBody Role role) {
        userService.saveRole(role);
    }

    @PostMapping("/add/role-to-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoleToUser(@RequestBody UserDTO userDTO) {
        User user = userService.userByUserName(userDTO.getUsername());
        List<String> rolesDTO = userDTO.getRoles();
        for (String roleDTO : rolesDTO) {
            userService.addRoleToUser(userDTO.getUsername(), roleDTO);
        }
    }

    @GetMapping("/get/{id}")
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

    @GetMapping("/check/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public Boolean userExistsByEmail(@PathVariable("email") String email) {
        return userService.userExistsByEmail(email);
    }

    @GetMapping("/check/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public Boolean existsByUsername(@PathVariable("username") String username) {
        return userService.userExistsByUsername(username);
    }

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }




}
