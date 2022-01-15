package com.danzigstudio.Social.Medium.Demo.user;

import com.danzigstudio.Social.Medium.Demo.profile.Profile;
import com.danzigstudio.Social.Medium.Demo.role.Role;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "t_user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    private String password;

    @Email
    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    private String username;

    @ManyToMany(fetch = EAGER)
    @JoinTable(	name = "t_user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Collection<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Profile profile;

    private User(final User.Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
        this.email = builder.email;
        this.id = builder.id;
        this.profile = builder.profile;
        this.username = builder.username;
        this.roles = builder.roles;
    }

    public static class Builder{
        private Long id;
        private String firstName;
        private String lastName;
        private String password;
        private String email;
        private Profile profile;
        private String username;
        private Collection<Role> roles;

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

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder profile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }
        public Builder roles(Collection<Role> roles) {
            this.roles = roles;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
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

    public Profile getProfile() {
        return profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
