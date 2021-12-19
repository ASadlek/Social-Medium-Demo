package com.danzigstudio.Social.Medium.Demo.follow;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.danzigstudio.Social.Medium.Demo.follow.FollowMapper.countingFollows;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    @Autowired
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Follow addFollow(Follow follow) {
        return followRepository.save(follow);
    }

    public FollowDTO countFollowed(Profile profile){
        return countingFollows( followRepository.whoProfileFollowed(profile).size(),followRepository.whoFollowedProfile(profile).size());
    }
    public Boolean checkFollow(Profile following, Profile followed){
        return followRepository.followRelationCheck(following, followed).size() == 1;
    }

    public List<Follow> whoProfileFollowedList(Profile profile){
        return followRepository.whoProfileFollowed(profile);
    }

    public List<Follow> whoFollowedProfileList(Profile profile){
        return followRepository.whoFollowedProfile(profile);
    }

    public void deleteFollow(Profile following, Profile followed){
        followRepository.deleteFollow(following, followed);
    }
}
