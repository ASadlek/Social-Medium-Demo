package com.danzigstudio.Social.Medium.Demo.profile;

import com.danzigstudio.Social.Medium.Demo.user.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileMapper {

    public static Profile createProfile(User user){
        return new Profile.Builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .user(user)
                .build();
    }

    public static ProfileDTO profileToProfileDTO(Profile profile){
        return new ProfileDTO.Builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .bio(profile.getBio())
                .id(profile.getId())
                .build();
    }

    public static List<ProfileDTO> profileToProfileDTO(List<Profile> profiles) {
        List<ProfileDTO> profileDTOS = new ArrayList<>();
        for(Profile profile : profiles) {
            profileDTOS.add(profileToProfileDTO(profile));
        }
        return profileDTOS;
    }
}

