package com.danzigstudio.Social.Medium.Demo.user;

public class UserMapper {

    public static User userDTOToUser(UserDTO userDTO){
        return new User.Builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .userRole(UserRole.valueOf(userDTO.getUserRole()))
                .build();
    }

    public static UserDTO userToUserDTO(User user){
        return new UserDTO.Builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .userRole(user.getUserRole().toString())
                .id(user.getId())
                .build();
    }

}

