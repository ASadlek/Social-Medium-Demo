package com.danzigstudio.Social.Medium.Demo.comment;

import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.user.User;


import java.time.LocalDateTime;

public class CommentMapper {

    public static Comment commentDTOToComment(CommentDTO commentDTO, User user, Post post) {
        return new Comment.Builder()
                .textContent(commentDTO.getTextContent())
                .user(user)
                .post(post)
                .likeReaction(0)
                .unlikeReaction(0)
                .timeRecord(LocalDateTime.now())
                .build();
    }
}
