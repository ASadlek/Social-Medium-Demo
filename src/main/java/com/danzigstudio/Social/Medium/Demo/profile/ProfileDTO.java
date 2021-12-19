package com.danzigstudio.Social.Medium.Demo.profile;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProfileDTO {

    private String firstName;
    private String lastName;
    private String bio;
    private Long id;

    private ProfileDTO(final Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.id = builder.id;
        this.bio = builder.bio;
    }

    public static class Builder{
        public String bio;
        private String firstName;
        private String lastName;
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
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder bio(String bio) {
            this.bio = bio;
            return this;
        }

        public ProfileDTO build(){
            return new ProfileDTO(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }
}
