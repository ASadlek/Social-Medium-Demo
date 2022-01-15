package com.danzigstudio.Social.Medium.Demo.user;

import com.danzigstudio.Social.Medium.Demo.role.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMapper {

    public static User userDTOToUser(UserDTO userDTO){
        return new User.Builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .build();
    }

    public static UserDTO userToUserDTO(User user){

        return new UserDTO.Builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(rolesToString(user))
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public static List<String> rolesToString(User user){
        List<String> listDTO = new ArrayList<>();
        Collection<Role> userRoles = user.getRoles();
        for (Role userRole : userRoles) {
           listDTO.add(userRole.getName());
        }
        return  listDTO;
    }

}

