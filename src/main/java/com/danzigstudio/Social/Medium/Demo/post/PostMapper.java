package com.danzigstudio.Social.Medium.Demo.post;


import com.danzigstudio.Social.Medium.Demo.user.User;

public class PostMapper {

       public static Post postDTOToPost(PostDTO postDTO, User user){
        return new Post.Builder()
                .description(postDTO.getDescription())
                .likeReaction(0)
                .unlikeReaction(0)
                .user(user)
                .build();
    }
}
