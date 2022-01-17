package com.danzigstudio.Social.Medium.Demo.user;

import com.danzigstudio.Social.Medium.Demo.role.Role;
import com.danzigstudio.Social.Medium.Demo.role.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service @Transactional @Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if (user == null){
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        } else{
            log.info("User found in database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }

    public User addUser (User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> userById (Long id){
        return userRepository.findById(id);
    }

    public User userByUserName (String username){ //usun bo jest inne???????? idk
        return userRepository.findByUsername(username);
    }

    public Role roleByName (String name){
        return roleRepository.findByName(name);
    }

    public Boolean userExistsByEmail (String email) { return userRepository.existsByEmail(email);}

    public Boolean userExistsByUsername (String username) { return userRepository.existsByUsername(username);}

    public Role saveRole (Role role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName){
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Boolean checkLettersInNames(User user) {
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
