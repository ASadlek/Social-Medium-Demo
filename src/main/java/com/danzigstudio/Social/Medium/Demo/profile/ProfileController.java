package com.danzigstudio.Social.Medium.Demo.profile;

import com.danzigstudio.Social.Medium.Demo.block.Block;
import com.danzigstudio.Social.Medium.Demo.block.BlockService;
import com.danzigstudio.Social.Medium.Demo.profile.profileException.ProfileBlockedException;
import com.danzigstudio.Social.Medium.Demo.role.Role;
import com.danzigstudio.Social.Medium.Demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.danzigstudio.Social.Medium.Demo.profile.ProfileMapper.profileToProfileDTO;


@RestController
@RequestMapping(path = "profile")
public class ProfileController {

    private final ProfileService profileService;
    private final BlockService blockService;

    @Autowired
    public ProfileController(ProfileService profileService, BlockService blockService) {
        this.profileService = profileService;
        this.blockService = blockService;
    }

    @GetMapping("/{id}/viewer:{viewerId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProfileDTO getProfileById(@PathVariable("id") Long id, @PathVariable("viewerId") Long viewerId) {
        Profile profile = profileService.profileById(id).get();
        Profile viewer = profileService.profileById(viewerId).get();
        if(blockService.checkBlock(viewer,profile) || blockService.checkBlock(profile, viewer)) throw new ProfileBlockedException("Access to the Profile denied due to block relation");
        else return profileToProfileDTO(profile);
    }

    @PostMapping("/{id}/add/bio")
    @ResponseStatus(HttpStatus.FOUND)
    public void addBio(@PathVariable("id") Long id, @RequestBody ProfileDTO profileDTO){
        Profile profile = profileService.profileById(id).get();
        profile.setBio(profileDTO.getBio());
        profileService.addProfile(profile);
    }



    @GetMapping("/viewer:{id}/find")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProfileDTO> findByName(@RequestParam Map<String,String> fullName, @PathVariable("id") Long id){
        String firstName = fullName.get("firstName");
        String lastName = fullName.get("lastName");
        Profile viewer = profileService.profileById(id).get();
        if(firstName != null && !firstName.trim().isEmpty() && lastName != null && !lastName.trim().isEmpty()){
            List<Profile> profiles = profileService.profileByFullName(firstName, lastName);
                for (Profile profile : profiles) {
                    if(blockService.checkBlock(viewer,profile) || blockService.checkBlock(profile, viewer)){
                        profiles.remove(profile);
                    }
                }
            return  profileToProfileDTO(profiles);

        } else if (firstName != null && !firstName.trim().isEmpty()){
            List<Profile> profiles = profileService.profileByFirstName(firstName);
            for (Profile profile : profiles) {
                if(blockService.checkBlock(viewer,profile) || blockService.checkBlock(profile, viewer)){
                    profiles.remove(profile);
                }
            }
            return  profileToProfileDTO(profiles);

        } else {
            List<Profile> profiles = profileService.profileByLastName(lastName);
            for (Profile profile : profiles) {
                if(blockService.checkBlock(viewer,profile) || blockService.checkBlock(profile, viewer)){
                    profiles.remove(profile);
                }
            }
            return  profileToProfileDTO(profiles);
        }
    }



    //    @GetMapping("/find/first_name")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.FOUND)
//    public List<ProfileDTO> findByFirstName(@RequestParam String firstName){
//        return  profileToProfileDTO(profileService.profileByFirstName(firstName));
//    }

//    @GetMapping("/find/last_name")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.FOUND)
//    public List<ProfileDTO> findByLastName(@RequestParam String lastName){
//        return  profileToProfileDTO(profileService.profileByLastName(lastName));
//    }
//
//    @GetMapping("/find/full_name")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.FOUND)
//    public List<ProfileDTO> findByFullName(@RequestParam Map<String,String> fullName){
//        String firstName = fullName.get("firstName");
//        String lastName = fullName.get("lastName");
//        return  profileToProfileDTO(profileService.profileByFullName(firstName, lastName));
//    }
}
