package com.tatisam.tasteit.services.auth.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tatisam.tasteit.entities.auth.Role;
import com.tatisam.tasteit.entities.auth.User;
import com.tatisam.tasteit.exceptions.auth.RegisterException;
import com.tatisam.tasteit.payload.auth.LoginDTO;
import com.tatisam.tasteit.payload.auth.SignUpDTO;
import com.tatisam.tasteit.repositories.app.CountryRepository;
import com.tatisam.tasteit.repositories.auth.RoleRepository;
import com.tatisam.tasteit.repositories.auth.UserRepository;
import com.tatisam.tasteit.services.auth.AuthService;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

/**
 * Authentication Service is used to register and login user to the application
 * @implSpec {@link AuthService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final Environment environment;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           CountryRepository countryRepository, Environment environment, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
        this.environment = environment;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Register {@link User} in the application
     * @param dto {@link SignUpDTO} object submitted by the user during registration
     * @return {@link String} with message about creating user
     * @throws RegisterException when username or email from dto already exists in the database
     * @since 28/02/22
     */
    @Override
    public String registerUser(SignUpDTO dto) {
        if (userRepository.existsUserByEmail(dto.getEmail())) {
            throw new RegisterException("email", dto.getEmail(), "Email already taken");
        }
        if (userRepository.existsUserByUserName(dto.getUserName())) {
            throw new RegisterException("userName", dto.getUserName(), "Username already taken");
        }
        var encodedPassword = passwordEncoder.encode(dto.getPassword());
        var user = User.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(encodedPassword)
                .roles(Collections.singleton(roleRepository.findRoleByName("ROLE_USER").orElse(Role.builder().name("ROLE_USER").build())))
                .build();
        userRepository.save(user);
        return "User created successfully";
    }

    /**
     * Login {@link User} to the application
     * @param dto {@link LoginDTO} object submitted by the user during login
     * @return {@link JWT} for logged in user
     * @throws UsernameNotFoundException when user not found
     * @since 28/02/22
     */
    @Override
    public String login(LoginDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(
                dto.getUserNameOrEmail(),
                dto.getPassword()
        );
        var authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = userRepository
                .findUserByUserNameOrEmail(dto.getUserNameOrEmail(), dto.getUserNameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User name or Email not found"));
        return getJWT(user);
    }

    /**
     * Get {@link JWT} for user
     * @param user {@link User} object
     * @return {@link JWT} for user
     * @throws NullPointerException when there is no jwtSecret or jwtExpires in the application environment
     * @since 28/02/22
     */
    @Override
    public String getJWT(User user) {
        var secret = environment.getProperty("com.tatisam.jwt.secret");
        var expires = environment.getProperty("com.tatisam.jwt.expires", Long.class);
        if (expires == null || secret == null) {
            throw new NullPointerException("Secret or expires not in properties");
        }
        return JWT.create()
                .withSubject(user.getUserName())
                .withExpiresAt(new Date(expires + System.currentTimeMillis()))
                .sign(Algorithm.HMAC512(secret));
    }
}
