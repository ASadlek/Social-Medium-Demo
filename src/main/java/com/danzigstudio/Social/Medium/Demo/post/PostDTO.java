package com.danzigstudio.Social.Medium.Demo.post;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostDTO {

    private String description;
    private Long idProfile;
    private int numberOfLikeReactions;
    private int numberOfUnlikeReactions;
    private int numberOfComments;

    private PostDTO(final Builder builder) {
        this.description = builder.description;
        this.idProfile = builder.idProfile;
        this.numberOfLikeReactions = builder.numberOfLikeReactions;
        this.numberOfUnlikeReactions = builder.numberOfUnlikeReactions;
        this.numberOfComments = builder.numberOfComments;
    }


    public static class Builder {
        private String description;
        private Long idProfile;
        private int numberOfLikeReactions;
        private int numberOfUnlikeReactions;
        private int numberOfComments;


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

    public int getNumberOfUnlikeReactions() {
        return numberOfUnlikeReactions;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }
}
