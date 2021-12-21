package com.danzigstudio.Social.Medium.Demo.post;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class PostDTO {

    private String description;
    private Long idProfile;
    private Long idPost;
    private int numberOfLikeReactions;
    private int numberOfUnlikeReactions;
    private int numberOfComments;
    private LocalDateTime timeRecord;
    private Long sharedPostId;

    private PostDTO(final Builder builder) {
        this.description = builder.description;
        this.idProfile = builder.idProfile;
        this.numberOfLikeReactions = builder.numberOfLikeReactions;
        this.numberOfUnlikeReactions = builder.numberOfUnlikeReactions;
        this.numberOfComments = builder.numberOfComments;
        this.timeRecord = builder.timeRecord;
        this.idPost = builder.idPost;
        this.sharedPostId = builder.sharedPostId;
    }


    public static class Builder {
        private String description;
        private Long idProfile;
        private Long idPost;
        private int numberOfLikeReactions;
        private int numberOfUnlikeReactions;
        private int numberOfComments;
        private LocalDateTime timeRecord;
        private Long sharedPostId;


        public Builder() {
        }

        public Builder idProfile(Long idProfile) {
            this.idProfile = idProfile;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder numberOfLikeReactions(int numberOfLikeReactions) {
            this.numberOfLikeReactions = numberOfLikeReactions;
            return this;
        }
        public Builder numberOfUnlikeReactions(int numberOfUnlikeReactions) {
            this.numberOfUnlikeReactions = numberOfUnlikeReactions;
            return this;
        }
        public Builder numberOfComments(int numberOfComments) {
            this.numberOfComments = numberOfComments;
            return this;
        }
        public Builder timeRecord(LocalDateTime timeRecord) {
            this.timeRecord = timeRecord;
            return this;
        }
        public Builder idPost(Long idPost) {
            this.idPost = idPost;
            return this;
        }
        public Builder sharedPostId(Long sharedPostId) {
            this.sharedPostId = sharedPostId;
            return this;
        }

        public PostDTO build(){
            return new PostDTO(this);
        }
    }

    public String getDescription() {
        return description;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public int getNumberOfLikeReactions() {
        return numberOfLikeReactions;
    }

    public Long getIdPost() {
        return idPost;
    }

    public LocalDateTime getTimeRecord() {
        return timeRecord;
    }

    public int getNumberOfUnlikeReactions() {
        return numberOfUnlikeReactions;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public Long getSharedPostId() {
        return sharedPostId;
    }
}
