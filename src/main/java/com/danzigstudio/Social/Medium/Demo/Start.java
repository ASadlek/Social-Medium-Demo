package com.danzigstudio.Social.Medium.Demo;

import com.danzigstudio.Social.Medium.Demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private UserRepository userRepository;

    @Autowired
    public Start(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}

