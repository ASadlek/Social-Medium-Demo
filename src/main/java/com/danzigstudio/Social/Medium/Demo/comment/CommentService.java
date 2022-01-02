package com.danzigstudio.Social.Medium.Demo.comment;

import com.danzigstudio.Social.Medium.Demo.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public Comment addComment(Comment comment){
        return commentRepository.save(comment);
    }

    public Optional<Comment> commentById (Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> commentsFromPost(int pageNumber, int elementsNumber, Post post) {
        Pageable pageByNumberAndElementsNumber = PageRequest.of(pageNumber, elementsNumber, Sort.by("id"));
        List<Comment> commentList = commentRepository.findCommentsByPost(post, pageByNumberAndElementsNumber);
        return commentList;
    }

    public void deleteComment(Long id) { commentRepository.deleteById(id);}


}
