package com.danzigstudio.Social.Medium.Demo.comment;

import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.post.PostService;
import com.danzigstudio.Social.Medium.Demo.user.User;
import com.danzigstudio.Social.Medium.Demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.danzigstudio.Social.Medium.Demo.comment.CommentMapper.commentDTOToComment;

@RestController
@RequestMapping(path = "comment")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService, UserService userService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment (@RequestBody CommentDTO commentDTO){
        User user = userService.userById(commentDTO.getIdUser()).get();
        Post post = postService.postById(commentDTO.getIdPost()).get();
        commentService.addComment(commentDTOToComment(commentDTO, user, post));
    }
}
