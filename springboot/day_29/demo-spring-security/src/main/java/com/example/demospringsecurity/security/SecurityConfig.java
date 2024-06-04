package com.example.demospringsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] paths = {"/phim-le", "/phim-bo", "/phim-chieu-rap", "/tin-tuc/**"};

        // Cấu hình path
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/user").hasRole("USER");
            auth.requestMatchers("/admin").hasRole("ADMIN");

            auth.requestMatchers("/css/**", "/js/**", "/image/**").permitAll();
            auth.requestMatchers(paths).permitAll();
            auth.requestMatchers("/author").hasAnyRole("ADMIN", "AUTHOR");
            auth.requestMatchers(HttpMethod.GET, "/aa/**", "/bb/**").hasRole("ADMIN");
            auth.requestMatchers("/abc", "/bcd").hasAuthority("ROLE_USER");
            auth.requestMatchers("/xxx", "/yyy").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
            auth.anyRequest().authenticated(); // Tất cả các request khác đều cần xác thực
        });

        // Cấu hình login
        http.formLogin(formLogin -> {
            formLogin.defaultSuccessUrl("/", true); // Nếu login thành công thì chuyển hướng về trang chủ
            formLogin.permitAll(); // Tất cả đều được truy cập vào trang login
        });

        // Cấu hình logout
        http.logout(logout -> {
            logout.logoutSuccessUrl("/"); // Nếu logout thành công thì chuyển hướng về trang chủ
            logout.deleteCookies("JSESSIONID"); // Xóa cookie
            logout.invalidateHttpSession(true); // Hủy session
            logout.clearAuthentication(true); // Xóa thông tin xác thực
            logout.permitAll(); // Tất cả đều được truy cập vào trang logout
        });

        return http.build();
    }
}
