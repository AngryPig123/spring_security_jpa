package org.spring.example.jpa.configure.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.configure.security.filter.CsrfCookieFilter;
import org.spring.example.jpa.configure.security.filter.CsrfTokenLoggerFilter;
import org.spring.example.jpa.configure.security.filter.CsrfTokenValidFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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

    private final CustomCorsConfig customCorsConfig;
    private final CsrfTokenLoggerFilter csrfTokenLoggerFilter;
    private final CsrfTokenValidFilter csrfTokenValidFilter;

    @Value("${spring.profiles.active}")
    private String ACTIVE;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        httpSecurity(http)
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("/basic").permitAll()
                                .requestMatchers("/admin").hasAuthority("admin_enter")
                                .requestMatchers("/user").hasAuthority("user_enter")
                                .requestMatchers("/guest").hasAuthority("guest_enter")
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    private HttpSecurity httpSecurity(HttpSecurity http) throws Exception {
        if (ACTIVE.equals("dev")) {
            return http
                    .cors(AbstractHttpConfigurer::disable)
                    .csrf(AbstractHttpConfigurer::disable);
        } else {
            CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
            requestHandler.setCsrfRequestAttributeName("_csrf");
            return http
                    .addFilterAfter(csrfTokenLoggerFilter, BasicAuthenticationFilter.class)
                    .addFilterAfter(csrfTokenValidFilter, BasicAuthenticationFilter.class)
                    .cors((cors) -> cors.configurationSource(customCorsConfig))
                    .csrf((csrf) -> csrf
                            .csrfTokenRequestHandler(requestHandler)
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    )
                    .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
        }
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();   //  ToBE bcrypt
    }

}
