package com.danzigstudio.Social.Medium.Demo.follow;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileDTO;

import java.util.ArrayList;
import java.util.List;

public class FollowMapper {

    public static Follow createFollow(Profile profileFollowed, Profile profileFollowing){
        return new Follow.Builder()
                .followed(profileFollowed)
                .following(profileFollowing)
                .build();
    }

    public static FollowDTO countingFollows(int followingNumber, int followedNumber){
        return new FollowDTO.Builder()
                .followingNumber(followingNumber)
                .followedNumber(followedNumber)
                .build();
    }

    public static ProfileDTO followingToProfileDTO(Follow follow){
        return new ProfileDTO.Builder()
                .id(follow.getFollowing().getId())
                .firstName(follow.getFollowing().getFirstName())
                .lastName(follow.getFollowing().getLastName())
                .build();
    }
    public static List<ProfileDTO> followingToProfileDTO(List<Follow> follows) {
        List<ProfileDTO> profileDTOS = new ArrayList<>();
        for(Follow follow : follows) {
            profileDTOS.add(followingToProfileDTO(follow));
        }
        return profileDTOS;
    }

    public static ProfileDTO followedToProfileDTO(Follow follow){
        return new ProfileDTO.Builder()
                .id(follow.getFollowed().getId())
                .firstName(follow.getFollowed().getFirstName())
                .lastName(follow.getFollowed().getLastName())
                .build();
    }
    public static List<ProfileDTO> followedToProfileDTO(List<Follow> follows) {
        List<ProfileDTO> profileDTOS = new ArrayList<>();
        for(Follow follow : follows) {
            profileDTOS.add(followedToProfileDTO(follow));
        }
        return profileDTOS;
    }

}

