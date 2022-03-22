package com.tatisam.tasteit.repositories.auth;

import com.tatisam.tasteit.entities.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for work with table roles {@link Role} from database
 * @implSpec JpaRepository
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(String name);
}
