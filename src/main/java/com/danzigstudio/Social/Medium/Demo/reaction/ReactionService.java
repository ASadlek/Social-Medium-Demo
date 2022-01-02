package com.danzigstudio.Social.Medium.Demo.reaction;

import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    public Reaction addReaction(Reaction reaction){
        return reactionRepository.save(reaction);
    }

    public void deleteReaction(Long id) { reactionRepository.deleteById(id);}

    public Boolean reactionToPostCheck(Profile profile, Post post){
        return reactionRepository.reactionToPostCheck(profile, post) != null;
    }

    public Boolean reactionToCommentCheck(Profile profile, Comment comment){
        return reactionRepository.reactionToCommentCheck(profile, comment) != null;
    }

    public Reaction reactionTypeToPostCheck(Profile profile, Post post){
        return reactionRepository.reactionToPostCheck(profile, post);
    }

    public Reaction reactionTypeToCommentCheck(Profile profile, Comment comment){
        return reactionRepository.reactionToCommentCheck(profile, comment);
    }


}
