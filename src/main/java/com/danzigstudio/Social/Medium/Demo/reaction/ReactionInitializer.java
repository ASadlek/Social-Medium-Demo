package com.danzigstudio.Social.Medium.Demo.reaction;

import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.post.Post;

public class ReactionInitializer {


    public static Comment commentReactionInitializer(ReactionType reactionType, Comment comment){ //are setters ok?
        if(reactionType.toString().equals("LIKE")){
            comment.setLikeReaction(comment.getLikeReaction()+1);
        } else{
            comment.setUnlikeReaction(comment.getUnlikeReaction()+1);
        }
        return comment;
    }

    public static Post postReactionInitializer(ReactionType reactionType, Post post){ //are setters ok?
        if(reactionType.toString().equals("LIKE")){
            post.setLikeReaction(post.getLikeReaction()+1);
        } else{
            post.setUnlikeReaction(post.getUnlikeReaction()+1);
        }
        return post;
    }



}
