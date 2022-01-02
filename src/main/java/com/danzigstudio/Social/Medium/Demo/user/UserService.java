package com.danzigstudio.Social.Medium.Demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser (User user){
        return userRepository.save(user);
    }

    public Optional<User> userById (Long id){
        return userRepository.findById(id);
    }

    public Boolean checkNames(User user) {
        char[] firstChars = user.getFirstName().toCharArray();
        char[] lastChars = user.getLastName().toCharArray();
        for (char c : firstChars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        for (char c : lastChars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

}
