package com.tatisam.tasteit.controllers.auth;

import com.tatisam.tasteit.payload.auth.LoginDTO;
import com.tatisam.tasteit.payload.auth.LoginResponseDTO;
import com.tatisam.tasteit.payload.auth.SignUpDTO;
import com.tatisam.tasteit.services.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * RestController for authentication user in the application
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@RestController
@RequestMapping("/api/1/auth")
@CrossOrigin(origins = {"http://localhost:8080"})
public class AuthControllers {
    private final AuthService authService;

    public AuthControllers(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Register user {@link com.tatisam.tasteit.entities.auth.User} to the application
     * @param dto {@link SignUpDTO} object submitted by the user during registration
     * @return {@link ResponseEntity} with status Ok and message about creating user
     * @since 28/02/22
     */
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpDTO dto) {
        return new ResponseEntity<String>(authService.registerUser(dto), HttpStatus.CREATED);
    }

    /**
     * Login user {@link com.tatisam.tasteit.entities.auth.User} to the application
     * @param dto {@link LoginDTO} object submitted by the user during login
     * @return {@link ResponseEntity} with status Ok and {@link LoginResponseDTO} for logged-in user
     * @since 28/02/22
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody LoginDTO dto) {
        String jwt = authService.login(dto);
        return ResponseEntity.ok(
                LoginResponseDTO.builder()
                        .userNameOrEmail(dto.getUserNameOrEmail())
                        .jwt(jwt)
                        .build()
        );
    }
}
