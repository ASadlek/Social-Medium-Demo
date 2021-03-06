package com.danzigstudio.Social.Medium.Demo.comment;

import com.danzigstudio.Social.Medium.Demo.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.post = :post")
    public List<Comment> findCommentsByPost(@Param("post") Post post, Pageable pageable);

}
