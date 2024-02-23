package org.spring.example.jpa.configure.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AngrySecurityConfiguration {

    private final CustomCorsConfig customCorsConfig;
    private final CsrfTokenLoggerFilter csrfTokenLoggerFilter;
    private final CsrfTokenValidFilter csrfTokenValidFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .addFilterAfter(csrfTokenLoggerFilter, BasicAuthenticationFilter.class) //
                .addFilterAfter(csrfTokenValidFilter, BasicAuthenticationFilter.class)
                .cors((cors) -> cors.configurationSource(customCorsConfig))
                .csrf((csrf)-> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(
                        (requests) -> requests.anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails admin = org.springframework.security.core.userdetails.User.withUsername("admin").password("12345").roles("admin").build();
        UserDetails user = org.springframework.security.core.userdetails.User.withUsername("user").password("12345").roles("user").build();
        UserDetails guest = org.springframework.security.core.userdetails.User.withUsername("guest").password("12345").roles("guest").build();
        return new InMemoryUserDetailsManager(admin, user, guest);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();   //  ToBE bcrypt
    }

}
