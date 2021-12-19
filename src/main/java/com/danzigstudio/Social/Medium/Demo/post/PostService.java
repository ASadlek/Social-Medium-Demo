package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Post> followedTimeline(int pageNumber, int elementsNumber, List<Profile> profiles) {
        List<Post> posts = new ArrayList<>();
        for(Profile profile : profiles) {
            posts.addAll(postRepository.followedPosts(profile));
        }
        PagedListHolder page = new PagedListHolder(posts);
        page.setPageSize(elementsNumber);
        page.setPage(pageNumber);
        return page.getPageList();
    }

}

