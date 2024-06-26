package com.example.demospringsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CustomFilter customFilter;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // Tắt CSRF

        // Cấu hình path
        String[] paths = {"/phim-le", "/phim-bo", "/phim-chieu-rap", "/tin-tuc/**"};
        http.authorizeHttpRequests(auth -> {
//            auth.requestMatchers("/").permitAll();
//            auth.requestMatchers("/user").hasRole("USER");
//            auth.requestMatchers("/admin").hasRole("ADMIN");
//
//            auth.requestMatchers("/css/**", "/js/**", "/image/**").permitAll();
//            auth.requestMatchers(paths).permitAll();
//            auth.requestMatchers("/author").hasAnyRole("ADMIN", "AUTHOR");
//            auth.requestMatchers(HttpMethod.GET, "/aa/**", "/bb/**").hasRole("ADMIN");
//            auth.requestMatchers("/abc", "/bcd").hasAuthority("ROLE_USER");
//            auth.requestMatchers("/xxx", "/yyy").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
//            auth.anyRequest().authenticated(); // Tất cả các request khác đều cần xác thực

            auth.anyRequest().permitAll();
        });

        // Cấu hình login
//        http.formLogin(formLogin -> {
//            formLogin.loginPage("/login"); // Trang login do mình thiết kế
//            formLogin.defaultSuccessUrl("/", true); // Nếu login thành công thì chuyển hướng về trang chủ
//            formLogin.loginProcessingUrl("/login-handle");
//            formLogin.usernameParameter("email");
//            formLogin.passwordParameter("pass");
//            formLogin.permitAll(); // Tất cả đều được truy cập vào trang login
//        });

        // Cấu hình logout
        http.logout(logout -> {
            logout.logoutSuccessUrl("/"); // Nếu logout thành công thì chuyển hướng về trang chủ
            logout.deleteCookies("JSESSIONID"); // Xóa cookie
            logout.invalidateHttpSession(true); // Hủy session
            logout.clearAuthentication(true); // Xóa thông tin xác thực
            logout.permitAll(); // Tất cả đều được truy cập vào trang logout
        });

        // Cấu hình xác thực
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
