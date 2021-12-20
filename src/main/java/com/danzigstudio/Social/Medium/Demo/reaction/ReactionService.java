package com.danzigstudio.Social.Medium.Demo.reaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    public Reaction addReaction (Reaction reaction){
        return reactionRepository.save(reaction);
    }

    public void deleteReaction(Long id) { reactionRepository.deleteById(id);}
}
