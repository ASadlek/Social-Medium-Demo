package com.danzigstudio.Social.Medium.Demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Post addPost(Post post){
       return postRepository.save(post);
    }

    public Optional<Post> postById (Long id){
        return postRepository.findById(id);
    }
}
