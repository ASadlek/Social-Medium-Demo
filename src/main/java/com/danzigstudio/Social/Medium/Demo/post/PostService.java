package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> postById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> timeline(int pageNumber, int elementsNumber) {
        Pageable pageByNumberAndElementsNumber = PageRequest.of(pageNumber, elementsNumber, Sort.by("id"));
        Page<Post> postPage = postRepository.findAll(pageByNumberAndElementsNumber);
        return postPage.toList();
    }
    public void deletePost(Long id) { postRepository.deleteById(id);}

    public List<Post> profileTimeline(int pageNumber, int elementsNumber, Profile profile) {
        Pageable pageByNumberAndElementsNumber = PageRequest.of(pageNumber, elementsNumber, Sort.by("id"));
        List<Post> postList = postRepository.findPostsByProfile(profile, pageByNumberAndElementsNumber);
        return postList;
    }

}

