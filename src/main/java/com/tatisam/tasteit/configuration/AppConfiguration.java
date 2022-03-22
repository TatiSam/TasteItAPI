package com.tatisam.tasteit.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class configuration for {@link com.tatisam.tasteit.security.JWTAuthenticationFilter} with  JWT Secret and JWT Expires
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "com.tatisam")
public class AppConfiguration {
    String jwtSecret = "2kwAf0aOoM";
    long jwtExpires = 86400000;
}
