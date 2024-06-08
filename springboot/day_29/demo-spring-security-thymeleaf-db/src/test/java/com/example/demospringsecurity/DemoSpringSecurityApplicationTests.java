package com.example.demospringsecurity;

import com.example.demospringsecurity.entity.Role;
import com.example.demospringsecurity.entity.User;
import com.example.demospringsecurity.repository.RoleRepository;
import com.example.demospringsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class DemoSpringSecurityApplicationTests {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void save_roles() {
        Role roleUser = Role.builder().name("USER").build();
        Role roleAdmin = Role.builder().name("ADMIN").build();

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
    }

    @Test
    void save_users() {
        Role roleUser = roleRepository.findByName("USER").orElse(null);
        Role roleAdmin = roleRepository.findByName("ADMIN").orElse(null);

        User user1 = User.builder()
                .name("Hien")
                .email("hien@gmail.com")
                .password(passwordEncoder.encode("123"))
                .enabled(true)
                .roles(List.of(roleUser, roleAdmin))
                .build();

        User user2 = User.builder()
                .name("Duy")
                .email("duy@gmail.com")
                .password(passwordEncoder.encode("123"))
                .enabled(true)
                .roles(List.of(roleUser))
                .build();

        User user3 = User.builder()
                .name("Hang")
                .email("hang@gmail.com")
                .password(passwordEncoder.encode("123"))
                .enabled(false)
                .roles(List.of(roleUser))
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
