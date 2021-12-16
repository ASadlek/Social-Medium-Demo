package com.danzigstudio.Social.Medium.Demo.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Comment> commentById (Long id){
        return commentRepository.findById(id);
    }
}
