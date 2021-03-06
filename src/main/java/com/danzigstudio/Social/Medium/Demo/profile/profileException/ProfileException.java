package com.danzigstudio.Social.Medium.Demo.profile.profileException;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ProfileException {

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime dateTime;

    public ProfileException(String message, HttpStatus httpStatus, LocalDateTime dateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
