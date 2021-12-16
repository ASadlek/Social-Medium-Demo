package com.danzigstudio.Social.Medium.Demo.reaction;

import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.user.User;

import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionObjectType.COMMENT;
import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionObjectType.POST;

public class ReactionMapper {

    public static Reaction reactionDTOToCommentReaction(ReactionDTO reactionDTO, User user, Comment comment){
        return new Reaction.Builder()
                .user(user)
                .reactionObjectType(COMMENT)
                .reactionType(ReactionType.valueOf(reactionDTO.getReactionType()))
                .comment(comment)
                .build();
    }

    public static Reaction reactionDTOToPostReaction(ReactionDTO reactionDTO, User user, Post post){
        return new Reaction.Builder()
                .user(user)
                .reactionObjectType(POST)
                .reactionType(ReactionType.valueOf(reactionDTO.getReactionType()))
                .post(post)
                .build();
    }
}
