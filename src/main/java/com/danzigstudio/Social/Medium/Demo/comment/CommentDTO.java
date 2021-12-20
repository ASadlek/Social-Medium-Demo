package com.danzigstudio.Social.Medium.Demo.comment;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class CommentDTO {

    private String textContent;
    private Long idProfile;
    private Long idComment;
    private Long idPost;
    private int numberOfLikeReactions;
    private int numberOfUnlikeReactions;
    private LocalDateTime timeRecord;

    private CommentDTO(final Builder builder) {
        this.textContent = builder.textContent;
        this.idProfile = builder.idProfile;
        this.idComment = builder.idComment;
        this.numberOfLikeReactions = builder.numberOfLikeReactions;
        this.numberOfUnlikeReactions = builder.numberOfUnlikeReactions;
        this.timeRecord = builder.timeRecord;
        this.idPost = builder.idPost;
    }


    public static class Builder {
        private String textContent;
        private Long idProfile;
        private Long idComment;
        private int numberOfLikeReactions;
        private int numberOfUnlikeReactions;
        private LocalDateTime timeRecord;
        private Long idPost;

        public Builder() {
        }

        public Builder idProfile(Long idProfile) {
            this.idProfile = idProfile;
            return this;
        }

        public Builder idComment(Long idComment) {
            this.idComment = idComment;
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
        public Builder timeRecord(LocalDateTime timeRecord) {
            this.timeRecord = timeRecord;
            return this;
        }


        public CommentDTO build(){
            return new CommentDTO(this);
        }
    }

    public LocalDateTime getTimeRecord() {
        return timeRecord;
    }

    public String getTextContent() {
        return textContent;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public Long getIdComment() {
        return idComment;
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
