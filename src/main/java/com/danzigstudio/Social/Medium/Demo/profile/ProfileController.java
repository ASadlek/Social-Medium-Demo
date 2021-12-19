package com.danzigstudio.Social.Medium.Demo.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.danzigstudio.Social.Medium.Demo.profile.ProfileMapper.profileToProfileDTO;


@RestController
@RequestMapping(path = "profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProfileDTO getProfileById(@PathVariable("id") Long id) {
        Profile profile = profileService.profileById(id).get();
        return profileToProfileDTO(profile);
    }

    @PostMapping("/{id}/add/bio")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBio(@PathVariable("id") Long id, @RequestBody ProfileDTO profileDTO){
        Profile profile = profileService.profileById(id).get();
        profile.setBio(profileDTO.getBio());
        profileService.addProfile(profile);
    }






}
