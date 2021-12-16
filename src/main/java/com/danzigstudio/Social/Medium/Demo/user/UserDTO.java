package com.danzigstudio.Social.Medium.Demo.user;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDTO {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String userRole;
    private String bio;
    private Long id;

    private UserDTO(final Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
        this.email = builder.email;
        this.userRole = builder.userRole;
        this.id = builder.id;
        this.bio = builder.bio;
    }

    public static class Builder{
        public String bio;
        private String firstName;
        private String lastName;
        private String password;
        private String email;
        private String userRole;
        private Long id;

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
        public Builder userRole(String userRole) {
            this.userRole = userRole;
            return this;
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder bio(String bio) {
            this.bio = bio;
            return this;
        }
        public UserDTO build(){
            return new UserDTO(this);
        }
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

    public String getUserRole() {
        return userRole;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }
}
