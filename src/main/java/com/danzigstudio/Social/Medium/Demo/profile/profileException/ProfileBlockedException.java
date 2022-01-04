package com.danzigstudio.Social.Medium.Demo.profile.profileException;

public class ProfileBlockedException extends RuntimeException{

    public ProfileBlockedException(String message) {
        super(message);
    }

    public ProfileBlockedException(String message, Throwable cause) {
        super(message, cause);
    }
}
