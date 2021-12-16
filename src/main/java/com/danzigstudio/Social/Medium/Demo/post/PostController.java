package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.user.User;
import com.danzigstudio.Social.Medium.Demo.user.UserService;
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
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost (@RequestBody PostDTO postDTO){
        User user = userService.userById(postDTO.getIdUser()).get();
        postService.addPost(postDTOToPost(postDTO, user));
    }

    @GetMapping("/page:{pageNumber}/{elementsNumber}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PostDTO> getTimeline(Pageable pageable, @PathVariable("pageNumber") int pageNumber, @PathVariable("elementsNumber") int elementsNumber) {
       return postToPostDTO(postService.timeline(pageNumber,elementsNumber));
    }
}
