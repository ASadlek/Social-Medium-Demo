package com.danzigstudio.Social.Medium.Demo.block;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    private BlockRepository blockRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public Block addBlock(Block block){
        return blockRepository.save(block);
    }

    public List<Block> whoProfileBlocked(Profile profile) {
        return blockRepository.whoProfileBlocked(profile);
    }

    public void deleteBlock(Profile blocker, Profile blocked){
        blockRepository.deleteBlock(blocker, blocked);
    }

    public Boolean checkBlock(Profile blocker, Profile blocked){
        return blockRepository.blockRelationCheck(blocker, blocked) == null;
    }
}
