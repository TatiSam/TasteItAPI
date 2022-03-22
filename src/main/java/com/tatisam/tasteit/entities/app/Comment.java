package com.tatisam.tasteit.entities.app;

import lombok.*;

import javax.persistence.*;

/**
 * Class Entity for Comment in application
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/03/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @ToString.Exclude
    private Country country;
}
