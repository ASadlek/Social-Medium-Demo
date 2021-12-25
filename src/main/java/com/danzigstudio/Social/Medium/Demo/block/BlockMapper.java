package com.danzigstudio.Social.Medium.Demo.block;

import com.danzigstudio.Social.Medium.Demo.follow.Follow;
import com.danzigstudio.Social.Medium.Demo.follow.FollowDTO;
import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileDTO;

import java.util.ArrayList;
import java.util.List;

public class BlockMapper {

    public static Block createBlock(Profile profileBlocker, Profile profileBlocked){
        return new Block.Builder()
                .blocker(profileBlocker)
                .blocked(profileBlocked)
                .build();
    }

 /*   public static BlockDTO blockToBlockDTO(Block block){
        return new BlockDTO.Builder()

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
    }*/

}

