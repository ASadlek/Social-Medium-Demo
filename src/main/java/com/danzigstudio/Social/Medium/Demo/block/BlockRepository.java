package com.danzigstudio.Social.Medium.Demo.block;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    @Query("select b from Block b where b.blocker = :blocker")
    public List<Block> whoProfileBlocked(@Param("blocker") Profile following);

    @Transactional
    @Modifying
    @Query("delete from Block b where b.blocker = :blocker and b.blocked = :blocked")
    public void deleteBlock(@Param("blocker") Profile blocker, @Param("blocked") Profile blocked);
}
