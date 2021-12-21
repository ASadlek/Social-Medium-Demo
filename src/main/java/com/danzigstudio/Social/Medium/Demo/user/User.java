package com.danzigstudio.Social.Medium.Demo.user;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    private User(final User.Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
        this.email = builder.email;
        this.userRole = builder.userRole;
        this.id = builder.id;
        this.profile = builder.profile;
    }

    public static class Builder{
        private Long id;
        private String firstName;
        private String lastName;
        private String password;
        private String email;
        private UserRole userRole;
        private Profile profile;

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
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder userRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder profile(Profile profile) {
            this.profile = profile;
            return this;
        }
        public User build(){
            return new User(this);
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
