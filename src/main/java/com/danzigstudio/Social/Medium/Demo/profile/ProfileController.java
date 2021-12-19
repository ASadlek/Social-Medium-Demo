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






}
