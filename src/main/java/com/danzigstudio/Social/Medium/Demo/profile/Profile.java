package com.danzigstudio.Social.Medium.Demo.profile;

import com.danzigstudio.Social.Medium.Demo.block.Block;
import com.danzigstudio.Social.Medium.Demo.comment.Comment;
import com.danzigstudio.Social.Medium.Demo.follow.Follow;
import com.danzigstudio.Social.Medium.Demo.post.Post;
import com.danzigstudio.Social.Medium.Demo.reaction.Reaction;
import com.danzigstudio.Social.Medium.Demo.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_profile")
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String bio;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Reaction> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL)
    private List<Follow> followedList = new ArrayList<>();

    @OneToMany(mappedBy = "blocker", cascade = CascadeType.ALL)
    private List<Block> blockerList = new ArrayList<>();

    @OneToMany(mappedBy = "blocked", cascade = CascadeType.ALL)
    private List<Block> blockedList = new ArrayList<>();

    private Profile(final Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.bio = builder.bio;
        this.id = builder.id;
        this.user = builder.user;
        this.posts = builder.posts;
        this.comments = builder.comments;
        this.reactions = builder.reactions;
        this.followingList = builder.followingList;
        this.followedList = builder.followedList;
        this.blockerList = builder.blockerList;
        this.blockedList = builder.blockedList;
    }

    public static class Builder{
        public String bio;
        private Long id;
        private String firstName;
        private String lastName;
        private User user;
        private List<Post> posts;
        private List<Comment> comments;
        private List<Reaction> reactions;
        private List<Follow> followingList;
        private List<Follow> followedList;
        private List<Block> blockerList;
        private List<Block> blockedList;

        public Builder() {
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder user(User user) {
            this.user = user;
            return this;
        }
        public Builder bio(String bio) {
            this.bio = bio;
            return this;
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder posts(List<Post> posts) {
            this.posts = posts;
            return this;
        }
        public Builder comments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }
        public Builder reactions(List<Reaction> reactions) {
            this.reactions = reactions;
            return this;
        }
        public Builder followingList(List<Follow> followingList) {
            this.followingList = followingList;
            return this;
        }
        public Builder followedList(List<Follow> followedList) {
            this.followedList = followedList;
            return this;
        }
        public Builder blockerList(List<Block> blockerList) {
            this.blockerList = blockerList;
            return this;
        }
        public Builder blockedList(List<Block> blockedList) {
            this.blockedList = blockedList;
            return this;
        }
        public Profile build(){
            return new Profile(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public User getUser() {
        return user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public List<Follow> getFollowingList() {
        return followingList;
    }

    public List<Follow> getFollowedList() {
        return followedList;
    }

    public List<Block> getBlockerList() {
        return blockerList;
    }

    public List<Block> getBlockedList() {
        return blockedList;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
