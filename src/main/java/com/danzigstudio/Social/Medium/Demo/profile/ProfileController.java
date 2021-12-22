package com.danzigstudio.Social.Medium.Demo.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.FOUND)
    public void addBio(@PathVariable("id") Long id, @RequestBody ProfileDTO profileDTO){
        Profile profile = profileService.profileById(id).get();
        profile.setBio(profileDTO.getBio());
        profileService.addProfile(profile);
    }

    @GetMapping("/find/first_name")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProfileDTO> findByFirstName(@RequestParam String firstName){
        return  profileToProfileDTO(profileService.profileByFirstName(firstName));
    }

    @GetMapping("/find/last_name")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProfileDTO> findByLastName(@RequestParam String lastName){
        return  profileToProfileDTO(profileService.profileByLastName(lastName));
    }

    @GetMapping("/find/full_name")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProfileDTO> findByFullName(@RequestParam Map<String,String> fullName){
        String firstName = fullName.get("firstName");
        String lastName = fullName.get("lastName");
        return  profileToProfileDTO(profileService.profileByFullName(firstName, lastName));
    }

   /* @PostMapping("/find/last_name")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Profile> findByLastName(@RequestBody ProfileDTO profileDTO){
        return  profileService.profileByLastName(profileDTO.getLastName());
    }
    @PostMapping("/find/full_name")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Profile> findByFullName(@RequestBody ProfileDTO profileDTO){
        return  profileService.profileByFullName(profileDTO.getFirstName(), profileDTO.getLastName());
    }*/





}
