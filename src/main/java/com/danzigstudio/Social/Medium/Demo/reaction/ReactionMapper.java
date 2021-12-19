package com.danzigstudio.Social.Medium.Demo.reaction;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.post.Post;

import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionObjectType.COMMENT;
import static com.danzigstudio.Social.Medium.Demo.reaction.ReactionObjectType.POST;

public class ReactionMapper {

    public static Reaction reactionDTOToCommentReaction(ReactionDTO reactionDTO, Profile profile, Comment comment){
        return new Reaction.Builder()
                .profile(profile)
                .reactionObjectType(COMMENT)
                .reactionType(ReactionType.valueOf(reactionDTO.getReactionType()))
                .comment(comment)
                .build();
    }

    public static Reaction reactionDTOToPostReaction(ReactionDTO reactionDTO, Profile profile, Post post){
        return new Reaction.Builder()
                .profile(profile)
                .reactionObjectType(POST)
                .reactionType(ReactionType.valueOf(reactionDTO.getReactionType()))
                .post(post)
                .build();
    }
}
