package com.danzigstudio.Social.Medium.Demo.reaction;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileDTO;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.comment.CommentService;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionInitializer.commentReactionInitializer;
import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionInitializer.postReactionInitializer;
import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionMapper.*;

@RestController
@RequestMapping(path = "reaction")
public class ReactionController {

    private final ReactionService reactionService;
    private final ProfileService profileService;
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public ReactionController(ReactionService reactionService, ProfileService profileService, PostService postService, CommentService commentService) {
        this.reactionService = reactionService;
        this.profileService = profileService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/add/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReactionToComment (@RequestBody ReactionDTO reactionDTO){
        Profile profile = profileService.profileById(reactionDTO.getIdProfile()).get();
        Comment comment = commentService.commentById(reactionDTO.getIdObject()).get();
        reactionService.addReaction(reactionDTOToCommentReaction(reactionDTO, profile, comment));
        commentService.addComment(commentReactionInitializer(ReactionType.valueOf(reactionDTO.getReactionType()), comment));
    }

    @PostMapping("/add/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReactionToPost (@RequestBody ReactionDTO reactionDTO){
        Profile profile = profileService.profileById(reactionDTO.getIdProfile()).get();
        Post post = postService.postById(reactionDTO.getIdObject()).get();
        reactionService.addReaction(reactionDTOToPostReaction(reactionDTO, profile, post));
        postService.addPost(postReactionInitializer(ReactionType.valueOf(reactionDTO.getReactionType()), post));
    }
    @DeleteMapping("/delete/{reactionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReaction(@PathVariable("reactionId") Long reactionId) {
        reactionService.deleteReaction(reactionId);
    }


    @GetMapping("/check/post:{postId}/{profileId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public Boolean postReactionCheck(@PathVariable("postId") Long postId, @PathVariable("profileId") Long profileId){
       Post post = postService.postById(postId).get();
       Profile profile = profileService.profileById(profileId).get();
       return reactionService.reactionToPostCheck(profile, post);
    }
    @GetMapping("/check/comment:{commentId}/{profileId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public Boolean commentReactionCheck(@PathVariable("commentId") Long commentId, @PathVariable("profileId") Long profileId){
        Comment comment = commentService.commentById(commentId).get();
        Profile profile = profileService.profileById(profileId).get();
        return reactionService.reactionToCommentCheck(profile, comment);
    }

    @GetMapping("/checkType/comment:{commentId}/{profileId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public ReactionDTO commentReactionTypeCheck(@PathVariable("commentId") Long commentId, @PathVariable("profileId") Long profileId){
        Comment comment = commentService.commentById(commentId).get();
        Profile profile = profileService.profileById(profileId).get();
        return reactionToReactionDTO(reactionService.reactionTypeToCommentCheck(profile, comment));
    }
    @GetMapping("/checkType/post:{postId}/{profileId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public ReactionDTO postReactionTypeCheck(@PathVariable("postId") Long postId, @PathVariable("profileId") Long profileId){
        Post post = postService.postById(postId).get();
        Profile profile = profileService.profileById(profileId).get();
        return reactionToReactionDTO(reactionService.reactionTypeToPostCheck(profile, post));
    }
}
