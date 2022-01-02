package com.danzigstudio.Social.Medium.Demo.reaction;

import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    @Query("select r from Reaction r where r.profile = :profile and r.post = :post")
    public Reaction reactionToPostCheck(@Param("profile") Profile profile, @Param("post") Post post);

    @Query("select r from Reaction r where r.profile = :profile and r.comment = :comment")
    public Reaction reactionToCommentCheck(@Param("profile") Profile profile, @Param("comment") Comment comment);

}
