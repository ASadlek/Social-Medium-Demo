package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.user.User;
import com.danzigstudio.Social.Medium.Demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.danzigstudio.Social.Medium.Demo.post.PostMapper.postDTOToPost;

@RestController
@RequestMapping(path = "post")
public class PostController {

    private final PostService postService;
    private final UserService userService; //???? ma sens? new constructor

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
}
