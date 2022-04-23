package com.tatisam.tasteit.security;

import com.tatisam.tasteit.services.auth.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Http Security Configuration
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final Environment environment;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(Environment environment, CustomUserDetailsService customUserDetailsService) {
        this.environment = environment;
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Singleton Encoder for password uses {@link BCryptPasswordEncoder}
     * @return {@link BCryptPasswordEncoder}
     * @since 28/02/22
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Singleton Authentication Manager
     * @return {@link BCryptPasswordEncoder}
     * @since 28/02/22
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configure authentication security in the application
     * @param http {@link HttpSecurity}
     * @since 28/02/22
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/1/**").permitAll()
                .antMatchers("/api/1/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.addFilterBefore(new JWTAuthenticationFilter(environment, customUserDetailsService),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
