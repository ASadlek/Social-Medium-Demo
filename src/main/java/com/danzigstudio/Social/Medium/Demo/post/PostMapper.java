package com.danzigstudio.Social.Medium.Demo.post;


import com.danzigstudio.Social.Medium.Demo.profile.Profile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostMapper {

       public static Post postDTOToPost(PostDTO postDTO, Profile profile){
        return new Post.Builder()
                .description(postDTO.getDescription())
                .likeReaction(0)
                .unlikeReaction(0)
                .profile(profile)
                .timeRecord(LocalDateTime.now())
                .build();
    }

    public static PostDTO postToPostDTO(Post post){
           return new PostDTO.Builder()
                   .description(post.getDescription())
                   .idProfile(post.getProfile().getId())
                   .numberOfLikeReactions(post.getLikeReaction())
                   .numberOfUnlikeReactions(post.getUnlikeReaction())
                   .numberOfComments(post.getComments().size())
                   .build();
    }

    public static List<PostDTO> postToPostDTO(List<Post> posts) {
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : posts) {
            postDTOS.add(postToPostDTO(post));
        }
        return postDTOS;
    }
   /* public static List<Post> timelinePosts(Long id) {
        List<Post> timelineResponse = new ArrayList<>();

        return timelineResponse;
    }*/
}
