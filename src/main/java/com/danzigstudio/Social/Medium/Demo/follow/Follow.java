package com.danzigstudio.Social.Medium.Demo.follow;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_follow")
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_following")
        private Profile following;

    @ManyToOne
    @JoinColumn(name="id_followed")
    private Profile followed;

    private Follow(final Builder builder) {
        this.id = builder.id;
        this.following = builder.following;
        this.followed = builder.followed;
    }
    public static class Builder {
        private Long id;
        private Profile following;
        private Profile followed;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder following(Profile following) {
            this.following = following;
            return this;
        }

        public Builder followed(Profile followed) {
            this.followed = followed;
            return this;
        }
        public Follow build(){
            return new Follow(this);
        }
    }

    public Long getId() {
        return id;
    }

    public Profile getFollowing() {
        return following;
    }

    public Profile getFollowed() {
        return followed;
    }
}
