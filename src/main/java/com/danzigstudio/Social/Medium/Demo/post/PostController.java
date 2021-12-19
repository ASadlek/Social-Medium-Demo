package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.follow.FollowService;
import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileDTO;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.danzigstudio.Social.Medium.Demo.follow.FollowMapper.followedToProfileDTO;
import static com.danzigstudio.Social.Medium.Demo.post.PostMapper.postDTOToPost;
import static com.danzigstudio.Social.Medium.Demo.post.PostMapper.postToPostDTO;

@RestController
@RequestMapping(path = "post")
public class PostController {

    private final PostService postService;
    private final ProfileService profileService;
    private final FollowService followService;

    @Autowired
    public PostController(PostService postService, ProfileService profileService, FollowService followService) {
        this.postService = postService;
        this.profileService = profileService;
        this.followService = followService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost (@RequestBody PostDTO postDTO){
        Profile profile = profileService.profileById(postDTO.getIdProfile()).get();
        postService.addPost(postDTOToPost(postDTO, profile));
    }

    @GetMapping("/timeline/all/page:{pageNumber}/{elementsNumber}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PostDTO> getTimeline(Pageable pageable, @PathVariable("pageNumber") int pageNumber, @PathVariable("elementsNumber") int elementsNumber) {
       return postToPostDTO(postService.timeline(pageNumber,elementsNumber));
    }

    @DeleteMapping("/delete/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/timeline/profile:{profileId}/page:{pageNumber}/{elementsNumber}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PostDTO> getTimelineForProfile(Pageable pageable, @PathVariable("pageNumber") int pageNumber, @PathVariable("elementsNumber") int elementsNumber, @PathVariable("profileId") Long profileId) {
        Profile profile = profileService.profileById(profileId).get();
        return postToPostDTO(postService.profileTimeline(pageNumber,elementsNumber, profile));
    }

    @GetMapping("/timeline/followed/profile:{profileId}/page:{pageNumber}/{elementsNumber}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PostDTO> getTimelineForFollowed(Pageable pageable, @PathVariable("pageNumber") int pageNumber, @PathVariable("elementsNumber") int elementsNumber, @PathVariable("profileId") Long profileId) {
        Profile profile = profileService.profileById(profileId).get();
        List<ProfileDTO> profileDTOS = followedToProfileDTO(followService.whoProfileFollowedList(profile));
        List<Profile> profiles = new ArrayList<>();
        for(ProfileDTO profileDTO : profileDTOS) {
            profiles.add(profileService.profileById(profileDTO.getId()).get());
        }
        return postToPostDTO(postService.followedTimeline(pageNumber, elementsNumber, profiles));
    }
    //@GetMapping("/{idPostToShare}/{idProfile}")
    //@ResponseStatus(HttpStatus.CREATED)

}
