package com.danzigstudio.Social.Medium.Demo.comment;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentDTO {

    private String textContent;
    private Long idUser;
    private Long idPost;

    private CommentDTO(final Builder builder) {
        this.textContent = builder.textContent;
        this.idUser = builder.idUser;
        this.idPost = builder.idPost;
    }


    public static class Builder {
        private String textContent;
        private Long idUser;
        private Long idPost;


        public Builder() {
        }

        public Builder idUser(Long idUser) {
            this.idUser = idUser;
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

        public CommentDTO build(){
            return new CommentDTO(this);
        }
    }

    public String getTextContent() {
        return textContent;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Long getIdPost() {
        return idPost;
    }
}
