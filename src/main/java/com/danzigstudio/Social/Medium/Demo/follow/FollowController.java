package com.danzigstudio.Social.Medium.Demo.follow;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileDTO;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.danzigstudio.Social.Medium.Demo.follow.FollowMapper.*;

@RestController
@RequestMapping(path = "follow")
public class FollowController {

    private FollowService followService;
    private ProfileService profileService;

    @Autowired
    public FollowController(FollowService followService, ProfileService profileService) {
        this.followService = followService;
        this.profileService = profileService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFollow(@RequestBody FollowDTO followDTO){
        Profile profileFollowed = profileService.profileById(followDTO.getFollowed()).get();
        Profile profileFollowing = profileService.profileById(followDTO.getFollowing()).get();
        if(profileFollowed != profileFollowing)
        followService.addFollow(createFollow(profileFollowed, profileFollowing));
        else throw new IllegalArgumentException("You cannot follow yourself!");
    }
    @GetMapping("/count/{profileId}")
    @ResponseStatus(HttpStatus.FOUND)
    public FollowDTO getFollowNumber(@PathVariable("profileId") Long profileId) {
        Profile profile = profileService.profileById(profileId).get();
        return followService.countFollowed(profile);
    }
    @GetMapping("/check/{followingId}/{followedId}")
    @ResponseStatus(HttpStatus.FOUND)
    public Boolean checkFollow(@PathVariable("followingId") Long followingId, @PathVariable("followedId") Long followedId) {
        Profile following = profileService.profileById(followingId).get();
        Profile followed = profileService.profileById(followedId).get();
        return followService.checkFollow(following, followed);
    }
    @GetMapping("/list/following/{profileId}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProfileDTO> followingList(@PathVariable("profileId") Long profileId) {
        Profile profile = profileService.profileById(profileId).get();
        return followingToProfileDTO(followService.whoFollowedProfileList(profile));
    }
    @GetMapping("/list/followed/{profileId}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProfileDTO> followedList(@PathVariable("profileId") Long profileId) {
        Profile profile = profileService.profileById(profileId).get();
        return followedToProfileDTO(followService.whoProfileFollowedList(profile));
    }
    @DeleteMapping("/delete/{followingId}/{followedId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFollow(@PathVariable("followingId") Long followingId, @PathVariable("followedId") Long followedId) {
        followService.deleteFollow(profileService.profileById(followingId).get(), profileService.profileById(followedId).get());
    }
}
