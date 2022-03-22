package com.tatisam.tasteit.services.auth.impl;

import com.tatisam.tasteit.repositories.auth.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Custom  {@link UserDetailsService} is used to retrieve user-related data
 * @implSpec {@link UserDetailsService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Locates the user based on the username
     * @param userNameOrEmail String
     * @return {@link User} that was found
     * @throws UsernameNotFoundException when there is no username or email in the database
     * @since 28/02/22
     */
    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        var user = userRepository.findUserByUserNameOrEmail(userNameOrEmail, userNameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not found: " + userNameOrEmail));
        var roles = user.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new User(user.getUserName(), user.getPassword(), roles);
    }
}
