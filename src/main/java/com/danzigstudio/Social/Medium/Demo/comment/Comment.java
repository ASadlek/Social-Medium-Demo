package com.danzigstudio.Social.Medium.Demo.comment;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_comment")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String textContent;

    @ManyToOne
    @JoinColumn(name="id_profile")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="id_post")
    private Post post;

    @Column
    private Integer likeReaction;

    @Column
    private Integer unlikeReaction;

    @Column
    private LocalDateTime timeRecord;


    private Comment(final Builder builder) {
        this.id = builder.id;
        this.textContent = builder.textContent;
        this.profile= builder.profile;
        this.post = builder.post;
        this.likeReaction = builder.likeReaction;
        this.unlikeReaction = builder.unlikeReaction;
        this.timeRecord = builder.timeRecord;
    }

    public static class Builder {
        private Long id;
        private String textContent;
        private Profile profile;
        private Post post;
        private Integer likeReaction;
        private Integer unlikeReaction;
        private LocalDateTime timeRecord;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder textContent(String textContent) {
            this.textContent = textContent;
            return this;
        }
        public Builder profile(Profile profile) {
            this.profile = profile;
            return this;
        }
        public Builder post(Post post) {
            this.post = post;
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
        public Comment build(){
            return new Comment(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getTextContent() {
        return textContent;
    }

    public Profile getProfile() {
        return profile;
    }

    public Post getPost() {
        return post;
    }

    public Integer getLikeReaction() {
        return likeReaction;
    }

    public Integer getUnlikeReaction() {
        return unlikeReaction;
    }

    public LocalDateTime getTimeRecord() {
        return timeRecord;
    }

    public void setLikeReaction(Integer likeReaction) {
        this.likeReaction = likeReaction;
    }

    public void setUnlikeReaction(Integer unlikeReaction) {
        this.unlikeReaction = unlikeReaction;
    }
}
