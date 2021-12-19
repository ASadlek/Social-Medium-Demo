package com.danzigstudio.Social.Medium.Demo.profile;

import com.danzigstudio.Social.Medium.Demo.user.User;

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


}

