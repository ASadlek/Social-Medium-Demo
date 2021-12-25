package com.danzigstudio.Social.Medium.Demo.block;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_block")
@NoArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_blocker")
    private Profile blocker;

    @ManyToOne
    @JoinColumn(name="id_blocked")
    private Profile blocked;

    private Block(final Builder builder) {
        this.id = builder.id;
        this.blocker = builder.blocker;
        this.blocked = builder.blocked;
    }

    public static class Builder {
        private Long id;
        private Profile blocker;
        private Profile blocked;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder blocker(Profile blocker) {
            this.blocker = blocker;
            return this;
        }

        public Builder blocked(Profile blocked) {
            this.blocked = blocked;
            return this;
        }
        public Block build(){
            return new Block(this);
        }
    }

    public Long getId() {
        return id;
    }

    public Profile getBlocker() {
        return blocker;
    }

    public Profile getBlocked() {
        return blocked;
    }
}
