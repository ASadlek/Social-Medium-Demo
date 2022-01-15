package com.danzigstudio.Social.Medium.Demo.user;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UserDTO {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<String> roles;
    private String username;
    private Long id;

    private UserDTO(final Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
        this.email = builder.email;
        this.roles = builder.roles;
        this.id = builder.id;
        this.username = builder.username;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private String password;
        private String email;
        private List<String> roles;
        private Long id;
        private String username;

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
        public Builder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder username(String username) {
            this.username = username;
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

    public List<String> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


}
