package com.danzigstudio.Social.Medium.Demo.post;


import com.danzigstudio.Social.Medium.Demo.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostMapper {

       public static Post postDTOToPost(PostDTO postDTO, User user){
        return new Post.Builder()
                .description(postDTO.getDescription())
                .likeReaction(0)
                .unlikeReaction(0)
                .user(user)
                .timeRecord(LocalDateTime.now())
                .build();
    }

    public static PostDTO postToPostDTO(Post post, Long id){
           return new PostDTO.Builder()
                   .description(post.getDescription())
                   .idUser(id)
                   .build();
    }

    public static List<PostDTO> postToPostDTO(List<Post> posts) {
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : posts) {
            postDTOS.add(postToPostDTO(post, post.getUser().getId()));
        }
        return postDTOS;
    }
   /* public static List<Post> timelinePosts(Long id) {
        List<Post> timelineResponse = new ArrayList<>();

        return timelineResponse;
    }*/
}
