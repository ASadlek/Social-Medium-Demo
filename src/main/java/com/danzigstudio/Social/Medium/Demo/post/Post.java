package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.user.User;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_post")
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @Column
    private Integer likeReaction;

    @Column
    private Integer unlikeReaction;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();


    private Post(final Post.Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.user = builder.user;
        this.likeReaction = builder.likeReaction;
        this.unlikeReaction = builder.unlikeReaction;
    }

    public static class Builder {
        private Long id;
        private String description;
        private User user;
        private Integer likeReaction;
        private Integer unlikeReaction;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder user(User user) {
            this.user = user;
            return this;
        }
        public Builder likeReaction(Integer likeReaction) {
            this.likeReaction = likeReaction;
            return this;
        }
        public Builder unlikeReaction(Integer unlikeReaction) {
            this.unlikeReaction = unlikeReaction;
            return this;
        }
        public Post build(){
            return new Post(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public Integer getLikeReaction() {
        return likeReaction;
    }

    public Integer getUnlikeReaction() {
        return unlikeReaction;
    }
}
