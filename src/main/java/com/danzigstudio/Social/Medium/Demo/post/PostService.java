package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.block.BlockService;
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
    private BlockService blockService;

    @Autowired
    public PostService(PostRepository postRepository, BlockService blockService) {
        this.postRepository = postRepository;
        this.blockService = blockService;
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> postById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> timeline(int pageNumber, int elementsNumber, Profile profile) {
        List<Post> posts = postRepository.findAll();
        List<Post> toRemove = new ArrayList<>();
        for(Post post : posts){
            if(blockService.checkBlock(profile, post.getProfile()) || blockService.checkBlock(post.getProfile(), profile))
            toRemove.add(post);
        }
        for(Post postToRemove : toRemove){
            posts.remove(postToRemove);
        }
        posts.remove(toRemove);
        PagedListHolder page = new PagedListHolder(posts);
        page.setPageSize(elementsNumber);
        page.setPage(pageNumber);
        return page.getPageList();
    }

    public void deletePost(Long id) { postRepository.deleteById(id);}

    public List<Post> profileTimeline(int pageNumber, int elementsNumber, Profile profile, Profile viewer) {
        if(blockService.checkBlock(profile, viewer) || blockService.checkBlock(viewer, profile)){
            throw new IllegalStateException();

        } else {
            Pageable pageByNumberAndElementsNumber = PageRequest.of(pageNumber, elementsNumber, Sort.by("id"));
            List<Post> postList = postRepository.findPostsByProfile(profile, pageByNumberAndElementsNumber);
            return postList;
        }
    }
    public List<Post> followedTimeline(int pageNumber, int elementsNumber, List<Profile> profiles, Profile profile) {
        List<Post> posts = new ArrayList<>();
        for(Profile profileFromList : profiles) {
            if(!blockService.checkBlock(profile, profileFromList))
            posts.addAll(postRepository.followedPosts(profileFromList));
        }
        PagedListHolder page = new PagedListHolder(posts);
        page.setPageSize(elementsNumber);
        page.setPage(pageNumber);
        return page.getPageList();
    }

}

