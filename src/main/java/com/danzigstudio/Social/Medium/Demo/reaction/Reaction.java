package com.danzigstudio.Social.Medium.Demo.reaction;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_reaction")
@NoArgsConstructor
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_profile")
    private Profile profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType reactionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionObjectType reactionObjectType;

    @ManyToOne
    @JoinColumn(name="id_post")
    private Post post;

    @ManyToOne
    @JoinColumn(name="id_comment")
    private Comment comment;

    private Reaction(final Builder builder) {
        this.id = builder.id;
        this.profile = builder.profile;
        this.reactionType = builder.reactionType;
        this.reactionObjectType = builder.reactionObjectType;
        this.post = builder.post;
        this.comment = builder.comment;
    }

    public static class Builder {
        private Long id;
        private Profile profile;
        private ReactionType reactionType;
        private ReactionObjectType reactionObjectType;
        private Post post;
        private Comment comment;


        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder profile(Profile profile) {
            this.profile = profile;
            return this;
        }
        public Builder reactionType(ReactionType reactionType) {
            this.reactionType = reactionType;
            return this;
        }
        public Builder reactionObjectType(ReactionObjectType reactionObjectType) {
            this.reactionObjectType = reactionObjectType;
            return this;
        }
        public Builder post(Post post) {
            this.post = post;
            return this;
        }
        public Builder comment(Comment comment) {
            this.comment = comment;
            return this;
        }

        public Reaction build(){
            return new Reaction(this);
        }
    }

    public Long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public ReactionObjectType getReactionObjectType() {
        return reactionObjectType;
    }

    public Post getPost() {
        return post;
    }

    public Comment getComment() {
        return comment;
    }



    public void setPost(Post post) {
        this.post = post;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
