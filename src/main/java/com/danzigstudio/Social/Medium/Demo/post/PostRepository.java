package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.profile = :profile")
    public List<Post> findPostsByProfile(@Param("profile") Profile profile, Pageable pageable);

    @Query("select p from Post p where p.profile = :profile")
    public List<Post> followedPosts(@Param("profile") Profile profile);

}
