package com.tatisam.tasteit.services.auth;

import com.tatisam.tasteit.entities.auth.User;
import com.tatisam.tasteit.payload.auth.LoginDTO;
import com.tatisam.tasteit.payload.auth.SignUpDTO;

/**
 * Interface for implementing by Authentication Service
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
public interface AuthService {
    String registerUser(SignUpDTO dto);
    String login(LoginDTO dto);
    String getJWT(User user);
}
