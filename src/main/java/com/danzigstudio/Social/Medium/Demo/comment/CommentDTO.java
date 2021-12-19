package com.danzigstudio.Social.Medium.Demo.comment;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentDTO {

    private String textContent;
    private Long idProfile;
    private Long idPost;
    private int numberOfLikeReactions;
    private int numberOfUnlikeReactions;

    private CommentDTO(final Builder builder) {
        this.textContent = builder.textContent;
        this.idProfile = builder.idProfile;
        this.idPost = builder.idPost;
        this.numberOfLikeReactions = builder.numberOfLikeReactions;
        this.numberOfUnlikeReactions = builder.numberOfUnlikeReactions;
    }


    public static class Builder {
        private String textContent;
        private Long idProfile;
        private Long idPost;
        private int numberOfLikeReactions;
        private int numberOfUnlikeReactions;


        public Builder() {
        }

        public Builder idProfile(Long idProfile) {
            this.idProfile = idProfile;
            return this;
        }

        public Builder idPost(Long idPost) {
            this.idPost = idPost;
            return this;
        }
        public Builder textContent(String textContent) {
            this.textContent = textContent;
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


        public CommentDTO build(){
            return new CommentDTO(this);
        }
    }

    public String getTextContent() {
        return textContent;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public Long getIdPost() {
        return idPost;
    }

    public int getNumberOfLikeReactions() {
        return numberOfLikeReactions;
    }

    public int getNumberOfUnlikeReactions() {
        return numberOfUnlikeReactions;
    }

}
