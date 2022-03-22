package com.tatisam.tasteit.entities.auth;

import lombok.*;

import javax.persistence.*;

/**
 * Class Entity for user roles
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
}
