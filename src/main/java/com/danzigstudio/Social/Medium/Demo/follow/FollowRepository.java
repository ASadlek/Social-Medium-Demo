package com.danzigstudio.Social.Medium.Demo.follow;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("select f from Follow f where f.followed = :followed")
    public List<Follow> whoFollowedProfile(@Param("followed") Profile followed);

    @Query("select f from Follow f where f.following = :following")
    public List<Follow> whoProfileFollowed(@Param("following") Profile following);

    @Query("select f from Follow f where f.following = :following and f.followed = :followed")
    public List<Follow> followRelationCheck(@Param("following") Profile following, @Param("followed") Profile followed);

    @Transactional
    @Modifying
    @Query("delete from Follow f where f.following = :following and f.followed = :followed")
    public void deleteFollow(@Param("following") Profile following, @Param("followed") Profile followed);
}
