package com.tatisam.tasteit.repositories.auth;

import com.tatisam.tasteit.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for work with table users {@link User} from database
 * @implSpec JpaRepository
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUserName(String userName);
    Optional<User> findUserByUserNameOrEmail(String userName, String email);
    Boolean existsUserByUserName(String userName);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByUserNameOrEmail(String userName, String email);
}
