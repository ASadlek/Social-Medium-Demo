package com.danzigstudio.Social.Medium.Demo.comment;

import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.post.PostService;
import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.danzigstudio.Social.Medium.Demo.comment.CommentMapper.commentDTOToComment;
import static com.danzigstudio.Social.Medium.Demo.comment.CommentMapper.commentToCommentDTO;

@RestController
@RequestMapping(path = "comment")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final ProfileService profileService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService, ProfileService profileService) {
        this.commentService = commentService;
        this.postService = postService;
        this.profileService = profileService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment (@RequestBody CommentDTO commentDTO){
        Profile profile = profileService.profileById(commentDTO.getIdProfile()).get();
        Post post = postService.postById(commentDTO.getIdPost()).get();
        commentService.addComment(commentDTOToComment(commentDTO, profile, post));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CommentDTO getCommentById(@PathVariable("id") Long id) {
        Comment comment = commentService.commentById(id).get();
        return commentToCommentDTO(comment);
    }

    @GetMapping("/post:{postId}/page:{pageNumber}/{elementsNumber}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CommentDTO> getCommentsFromPost(Pageable pageable, @PathVariable("pageNumber") int pageNumber, @PathVariable("elementsNumber") int elementsNumber, @PathVariable("postId") Long postId) {
        Post post = postService.postById(postId).get();
        return commentToCommentDTO(commentService.commentsFromPost(pageNumber,elementsNumber, post));
    }

    @PostMapping("/{commentId}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentDTO commentDTO){
        Comment comment = commentService.commentById(commentId).get();
        comment.setTextContent(commentDTO.getTextContent());
        commentService.addComment(comment);
    }

    @DeleteMapping("/delete/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }
}
