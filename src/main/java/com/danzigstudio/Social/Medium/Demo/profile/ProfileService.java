package com.danzigstudio.Social.Medium.Demo.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Optional<Profile> profileById(Long id){
        return profileRepository.findById(id);
    }

    public Profile addProfile(Profile profile){ return profileRepository.save(profile);}

}
