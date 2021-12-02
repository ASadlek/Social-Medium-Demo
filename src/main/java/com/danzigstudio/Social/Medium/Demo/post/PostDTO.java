package com.danzigstudio.Social.Medium.Demo.post;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostDTO {

    private String description;
    private Long idUser;

    private PostDTO(final Builder builder) {
        this.description = builder.description;
        this.idUser = builder.idUser;
    }


    public static class Builder {
        private String description;
        private Long idUser;


        public Builder() {
        }

        public Builder idUser(Long idUser) {
            this.idUser = idUser;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public PostDTO build(){
            return new PostDTO(this);
        }
    }

    public String getDescription() {
        return description;
    }

    public Long getIdUser() {
        return idUser;
    }
}
