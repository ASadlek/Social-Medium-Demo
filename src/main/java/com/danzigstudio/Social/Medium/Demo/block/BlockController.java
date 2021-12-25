package com.danzigstudio.Social.Medium.Demo.block;


import com.danzigstudio.Social.Medium.Demo.comment.CommentDTO;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileDTO;
import com.danzigstudio.Social.Medium.Demo.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.danzigstudio.Social.Medium.Demo.block.BlockMapper.createBlock;
import static com.danzigstudio.Social.Medium.Demo.comment.CommentMapper.commentDTOToComment;
import static com.danzigstudio.Social.Medium.Demo.follow.FollowMapper.createFollow;
import static com.danzigstudio.Social.Medium.Demo.profile.ProfileMapper.profileToProfileDTO;

@RestController
@RequestMapping(path = "block")
public class BlockController {

    private BlockService blockService;
    private ProfileService profileService;

    @Autowired
    public BlockController(BlockService blockService, ProfileService profileService) {
        this.blockService = blockService;
        this.profileService = profileService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBlock (@RequestBody BlockDTO blockDTO){
        Profile profileBlocker = profileService.profileById(blockDTO.getBlocker()).get();
        Profile profileBlocked = profileService.profileById(blockDTO.getBlocked()).get();
        blockService.addBlock(createBlock(profileBlocker, profileBlocked));
    }

    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProfileDTO> blockedList(@PathVariable("id") Long id){
        Profile profile = profileService.profileById(id).get();
        List<Block> blocks = blockService.whoProfileBlocked(profile);
        List<ProfileDTO> profileDTOS = new ArrayList<>();
        for(Block block : blocks) {
            profileDTOS.add(profileToProfileDTO(block.getBlocked()));
        }
        return profileDTOS;
    }

    @DeleteMapping("/delete/{blockerId}/{blockedId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBlock(@PathVariable("blockerId") Long blockerId, @PathVariable("blockedId") Long blockedId) {
        blockService.deleteBlock(profileService.profileById(blockerId).get(), profileService.profileById(blockedId).get());
    }

    //add mechanizmy block - usuwanie follow, brak w wyszukiwaniach, brak profile/post/comment
}
