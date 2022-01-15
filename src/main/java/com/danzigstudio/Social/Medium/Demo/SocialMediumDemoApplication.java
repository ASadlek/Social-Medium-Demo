package com.danzigstudio.Social.Medium.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SocialMediumDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediumDemoApplication.class, args);
		System.out.println("Ready to go!");

	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.addUser(new User.Builder()
//					.firstName("Andy")
//					.lastName("Warhol")
//					.password("123")
//					.email("userZero@gmail.com")
//					.username("userZero")
//					//.roles(List<Role> roles)
//					.build());
//			userService.addRoleToUser("userZero", "USER");
//		};
//	}

}
