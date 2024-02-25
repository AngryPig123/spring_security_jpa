package org.spring.example.jpa.configure.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.configure.security.filter.CsrfCookieFilter;
import org.spring.example.jpa.configure.security.filter.CsrfTokenLoggerFilter;
import org.spring.example.jpa.configure.security.filter.CsrfTokenValidFilter;
import org.spring.example.jpa.configure.security.handler.CustomAuthenticationFailureHandler;
import org.spring.example.jpa.configure.security.handler.CustomAuthenticationSuccessHandler;
import org.spring.example.jpa.configure.security.handler.CustomCsrfTokenRequestAttributeHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AngrySecurityConfiguration {

    /* handler */
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Value("${spring.profiles.active}")
    private String ACTIVE;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        httpSecurity(http)
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("**.ico", "/css/**", "/js/**", "/").permitAll()
                                .requestMatchers("/main").permitAll()
                                .requestMatchers("/basic").permitAll()
                                .requestMatchers("/login-form/**").permitAll()
                                .requestMatchers("/admin").hasAuthority("admin_enter")
                                .requestMatchers("/user").hasAuthority("user_enter")
                                .requestMatchers("/guest").hasAuthority("guest_enter")
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login-form")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login")
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler)
                        .permitAll()
                );


        return http.build();
    }

    private HttpSecurity httpSecurity(HttpSecurity http) throws Exception {
        if (ACTIVE.equals("test")) {
            return http
                    .cors(AbstractHttpConfigurer::disable)
                    .csrf(AbstractHttpConfigurer::disable);
        } else {

            return http
                    .addFilterAfter(new CsrfTokenLoggerFilter(), BasicAuthenticationFilter.class)
                    .addFilterAfter(new CsrfTokenValidFilter(), BasicAuthenticationFilter.class)
                    .cors((cors) -> cors.configurationSource(new CustomCorsConfig()))
                    .csrf((csrf) -> csrf
                            .csrfTokenRequestHandler(new CustomCsrfTokenRequestAttributeHandler())
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    )
                    .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
        }
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
