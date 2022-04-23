package com.tatisam.tasteit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.tatisam.tasteit.exceptions.auth.TokenException;
import com.tatisam.tasteit.services.auth.impl.CustomUserDetailsService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Authentication Filter for {@link JWT}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final Environment environment;
    private final CustomUserDetailsService userDetailsService;

    public JWTAuthenticationFilter(Environment environment, CustomUserDetailsService userDetailsService) {
        this.environment = environment;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Find and authenticate user {@link org.springframework.security.core.userdetails.User} in the application
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param filterChain {@link FilterChain}
     * @since 28/02/22
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJWTFromRequest(request);
        var user = validateToken(jwt);
        if (user.isPresent()){
            var userName = user.get();
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            var auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                    null, userDetails.getAuthorities());
            auth.setDetails(request);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Get {@link Optional} {@link String} with user by {@link JWT} token
     * @param jwt {@link String}
     * @return {@link Optional} {@link String} with user
     * @throws TokenException when token is invalid or expired
     * @since 28/02/22
     */
    public Optional<String> validateToken(String jwt) {
        String secret = environment.getProperty("com.tatisam.jwt.secret");
        if (StringUtils.hasText(secret) && StringUtils.hasText(jwt)) {
            try {
                String user = JWT.require(Algorithm.HMAC512(secret))
                        .build()
                        .verify(jwt)
                        .getSubject();
                boolean hasUser = user != null;
                return hasUser ? Optional.of(user) : Optional.empty();
            } catch (TokenExpiredException e) {
                throw new TokenException(jwt, "Token Expired");
            } catch (JWTVerificationException e) {
                throw new TokenException(jwt, "Invalid Token");
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Get {@link JWT} from {@link HttpServletRequest}
     * @param request {@link HttpServletRequest}
     * @return {@link String} with bearerToken
     * @since 28/02/22
     */
    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
