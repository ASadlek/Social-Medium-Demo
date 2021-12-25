package com.danzigstudio.Social.Medium.Demo.block;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BlockDTO {

    private Long blocker;
    private Long blocked;

    private BlockDTO(final Builder builder) {
        this.blocker = builder.blocker;
        this.blocked = builder.blocked;
    }

    public static class Builder {
        private Long blocker;
        private Long blocked;

        public Builder() {
        }

        public Builder blocker(Long blocker) {
            this.blocker = blocker;
            return this;
        }
        public Builder blocked(Long blocked) {
            this.blocked = blocked;
            return this;
        }

        public BlockDTO build(){
            return new BlockDTO(this);
        }
    }

    public Long getBlocker() {
        return blocker;
    }

    public Long getBlocked() {
        return blocked;
    }
}
