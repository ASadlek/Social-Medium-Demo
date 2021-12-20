package com.danzigstudio.Social.Medium.Demo.comment;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.post.Post;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static Comment commentDTOToComment(CommentDTO commentDTO, Profile profile, Post post) {
        return new Comment.Builder()
                .textContent(commentDTO.getTextContent())
                .profile(profile)
                .post(post)
                .likeReaction(0)
                .unlikeReaction(0)
                .timeRecord(LocalDateTime.now())
                .build();
    }
    public static CommentDTO commentToCommentDTO(Comment comment) {
        return new CommentDTO.Builder()
                .textContent(comment.getTextContent())
                .idProfile(comment.getProfile().getId())
                .numberOfLikeReactions(comment.getLikeReaction())
                .numberOfUnlikeReactions(comment.getUnlikeReaction())
                .timeRecord(comment.getTimeRecord())
                .idComment(comment.getId())
                .build();
    }
    public static List<CommentDTO> commentToCommentDTO(List<Comment> comments) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment : comments) {
            commentDTOS.add(commentToCommentDTO(comment));
        }
        return commentDTOS;
    }
}
