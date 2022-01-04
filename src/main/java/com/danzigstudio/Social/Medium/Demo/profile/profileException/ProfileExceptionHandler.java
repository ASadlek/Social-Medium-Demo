package com.danzigstudio.Social.Medium.Demo.profile.profileException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProfileExceptionHandler {

    @ExceptionHandler(value = {ProfileBlockedException.class})
    public ResponseEntity<Object> handleProfileBlockedException(ProfileBlockedException e){
        ProfileException profileException = new ProfileException(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(profileException, HttpStatus.BAD_REQUEST);
    }
}
