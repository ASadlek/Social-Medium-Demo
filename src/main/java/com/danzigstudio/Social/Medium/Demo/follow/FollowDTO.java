package com.danzigstudio.Social.Medium.Demo.follow;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FollowDTO {

    private Long following;
    private Long followed;
    private int followingNumber;
    private int followedNumber;

    private FollowDTO(final Builder builder) {
        this.following = builder.following;
        this.followed = builder.followed;
        this.followingNumber = builder.followingNumber;
        this.followedNumber = builder.followedNumber;
    }

    public static class Builder {
        private Long following;
        private Long followed;
        private int followingNumber;
        private int followedNumber;

        public Builder() {
        }

        public Builder following(Long following) {
            this.following = following;
            return this;
        }
        public Builder followed(Long followed) {
            this.followed = followed;
            return this;
        }
        public Builder followingNumber(int followingNumber) {
            this.followingNumber = followingNumber;
            return this;
        }
        public Builder followedNumber(int followedNumber) {
            this.followedNumber = followedNumber;
            return this;
        }

        public FollowDTO build(){
            return new FollowDTO(this);
        }
    }

    public Long getFollowing() {
        return following;
    }

    public Long getFollowed() {
        return followed;
    }

    public int getFollowingNumber() {
        return followingNumber;
    }

    public int getFollowedNumber() {
        return followedNumber;
    }
}
