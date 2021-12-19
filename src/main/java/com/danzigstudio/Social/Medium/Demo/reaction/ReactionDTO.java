package com.danzigstudio.Social.Medium.Demo.reaction;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReactionDTO {

    private Long idProfile;
    private String reactionType;
    private Long idObject;

    private ReactionDTO(final Builder builder) {
        this.idProfile = builder.idProfile;
        this.reactionType = builder.reactionType;
        this.idObject = builder.idObject;
    }


    public static class Builder {
        private Long idProfile;
        private String reactionType;
        private Long idObject;

        public Builder() {
        }

        public Builder idProfile(Long idProfile) {
            this.idProfile = idProfile;
            return this;
        }
        public Builder reactionType(String reactionType) {
            this.reactionType = reactionType;
            return this;
        }
        public Builder idObject(Long idObject) {
            this.idObject = idObject;
            return this;
        }

        public ReactionDTO build(){
            return new ReactionDTO(this);
        }
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public String getReactionType() {
        return reactionType;
    }


    public Long getIdObject() {
        return idObject;
    }
}
