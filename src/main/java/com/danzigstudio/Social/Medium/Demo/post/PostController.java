package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.danzigstudio.Social.Medium.Demo.post.PostMapper.postDTOToPost;
import static com.danzigstudio.Social.Medium.Demo.post.PostMapper.postToPostDTO;

@RestController
@RequestMapping(path = "post")
public class PostController {

    private final PostService postService;
    private final ProfileService profileService;

    @Autowired
    public PostController(PostService postService, ProfileService profileService) {
        this.postService = postService;
        this.profileService = profileService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost (@RequestBody PostDTO postDTO){
        Profile profile = profileService.profileById(postDTO.getIdProfile()).get();
        postService.addPost(postDTOToPost(postDTO, profile));
    }

    @GetMapping("/page:{pageNumber}/{elementsNumber}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PostDTO> getTimeline(Pageable pageable, @PathVariable("pageNumber") int pageNumber, @PathVariable("elementsNumber") int elementsNumber) {
       return postToPostDTO(postService.timeline(pageNumber,elementsNumber));
    }

    @DeleteMapping("/delete/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/profile:{profileId}/page:{pageNumber}/{elementsNumber}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PostDTO> getTimelineForProfile(Pageable pageable, @PathVariable("pageNumber") int pageNumber, @PathVariable("elementsNumber") int elementsNumber, @PathVariable("profileId") Long profileId) {
        Profile profile = profileService.profileById(profileId).get();
        return postToPostDTO(postService.profileTimeline(pageNumber,elementsNumber, profile));
    }

    //@GetMapping("/{idPostToShare}/{idProfile}")
    //@ResponseStatus(HttpStatus.CREATED)

}
