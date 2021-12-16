package com.danzigstudio.Social.Medium.Demo.reaction;

import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.comment.CommentService;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.post.PostService;
import com.danzigstudio.Social.Medium.Demo.user.User;
import com.danzigstudio.Social.Medium.Demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionInitializer.commentReactionInitializer;
import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionInitializer.postReactionInitializer;
import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionMapper.reactionDTOToCommentReaction;
import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionMapper.reactionDTOToPostReaction;

@RestController
@RequestMapping(path = "reaction")
public class ReactionController {

    private final ReactionService reactionService;
    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public ReactionController(ReactionService reactionService, UserService userService, PostService postService, CommentService commentService) {
        this.reactionService = reactionService;
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/add/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReactionToComment (@RequestBody ReactionDTO reactionDTO){
        User user = userService.userById(reactionDTO.getIdUser()).get();
        Comment comment = commentService.commentById(reactionDTO.getIdObject()).get();
        reactionService.addReaction(reactionDTOToCommentReaction(reactionDTO, user, comment));
        commentService.addComment(commentReactionInitializer(ReactionType.valueOf(reactionDTO.getReactionType()), comment));
    }

    @PostMapping("/add/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReactionToPost (@RequestBody ReactionDTO reactionDTO){
        User user = userService.userById(reactionDTO.getIdUser()).get();
        Post post = postService.postById(reactionDTO.getIdObject()).get();
        reactionService.addReaction(reactionDTOToPostReaction(reactionDTO, user, post));
        postService.addPost(postReactionInitializer(ReactionType.valueOf(reactionDTO.getReactionType()), post));

    }
}
