package com.danzigstudio.Social.Medium.Demo.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import com.danzigstudio.Social.Medium.Demo.role.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.danzigstudio.Social.Medium.Demo.profile.ProfileMapper.createProfile;
import static com.danzigstudio.Social.Medium.Demo.user.UserMapper.userDTOToUser;
import static com.danzigstudio.Social.Medium.Demo.user.UserMapper.userToUserDTO;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
        if(userService.checkLettersInNames(user)){
            if(!userService.userExistsByUsername(user.getUsername())){
                if(!userService.userExistsByEmail(user.getEmail())){
                    userService.addUser(user);
                    profileService.addProfile(createProfile(user));
                } else throw new IllegalArgumentException("Email is taken");
            } else throw new IllegalArgumentException("Username already exists");
        } else throw new IllegalArgumentException("Only letters can be used in names");
    }

    @PostMapping("/add/role/new-role")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRole(@RequestBody Role role) {
        userService.saveRole(role);
    }

    @PostMapping("/add/role/to-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoleToUser(@RequestBody UserDTO userDTO) {
        User user = userService.userByUsername(userDTO.getUsername());
        List<String> rolesDTO = userDTO.getRoles();
        for (String roleDTO : rolesDTO) {
            userService.addRoleToUser(userDTO.getUsername(), roleDTO);
        }
    }

    @GetMapping("/get/id:{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO getUserById(@PathVariable("id") Long id) {
        User user = userService.userById(id).get();
        return userToUserDTO(user);
    }

    @GetMapping("/get/username:{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO getUserByUsername(@PathVariable("username") String username) {
        User user = userService.userByUsername(username);
        return userToUserDTO(user);
    }

//    @PostMapping("/{id}/update/password")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void updatePassword(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
//        User user = userService.userById(id).get();
//        user.setPassword(userDTO.getPassword());
//        userService.addUser(user);
//    }

    @PostMapping("/{id}/update/first_name")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateFirstName(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        User user = userService.userById(id).get();
//        user.setFirstName(userDTO.getFirstName());
//        userService.addUser(user);
        Profile profile = user.getProfile();
        profile.setFirstName(userDTO.getFirstName());
        profileService.addProfile(profile);
    }

    @PostMapping("/{id}/update/last_name")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateLastName(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        User user = userService.userById(id).get();
//        user.setLastName(userDTO.getLastName());
//        userService.addUser(user);
        Profile profile = user.getProfile();
        profile.setLastName(userDTO.getLastName());
        profileService.addProfile(profile);
    }

//    @PostMapping("/{id}/update/email")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void updateEmail(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
//        User user = userService.userById(id).get();
//        user.setEmail(userDTO.getEmail());
//        userService.addUser(user);
//    }

    @GetMapping("/check/email:{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public Boolean userExistsByEmail(@PathVariable("email") String email) {
        return userService.userExistsByEmail(email);
    }

    @GetMapping("/check/username:{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public Boolean existsByUsername(@PathVariable("username") String username) {
        return userService.userExistsByUsername(username);
    }

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getAllUsers(){
        return userToUserDTO(userService.getAllUsers());
    }

    @PostMapping("/token/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.userByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);

            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }


}
