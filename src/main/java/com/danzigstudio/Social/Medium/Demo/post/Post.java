package com.danzigstudio.Social.Medium.Demo.post;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.reaction.Reaction;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JoinColumn(name="id_profile")
    private Profile profile;

    @Column
    private Integer likeReaction;

    @Column
    private Integer unlikeReaction;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reaction> reactions = new ArrayList<>();

    @Column
    private LocalDateTime timeRecord;

    private Post(final Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.profile = builder.profile;
        this.likeReaction = builder.likeReaction;
        this.unlikeReaction = builder.unlikeReaction;
        this.timeRecord = builder.timeRecord;
        this.comments = builder.comments;
        this.reactions = builder.reactions;
    }

    public static class Builder {
        private Long id;
        private String description;
        private Profile profile;
        private Integer likeReaction;
        private Integer unlikeReaction;
        private LocalDateTime timeRecord;
        private List<Comment> comments;
        private List<Reaction> reactions;

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
        public Builder profile(Profile profile) {
            this.profile = profile;
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

        public Builder timeRecord(LocalDateTime timeRecord) {
            this.timeRecord = timeRecord;
            return this;
        }

        public Builder comments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }
        public Builder reactions(List<Reaction> reactions) {
            this.reactions = reactions;
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

    public Profile getProfile() {
        return profile;
    }

    public Integer getLikeReaction() {
        return likeReaction;
    }

    public Integer getUnlikeReaction() {
        return unlikeReaction;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public LocalDateTime getTimeRecord() {
        return timeRecord;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setLikeReaction(Integer likeReaction) {
        this.likeReaction = likeReaction;
    }

    public void setUnlikeReaction(Integer unlikeReaction) {
        this.unlikeReaction = unlikeReaction;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
