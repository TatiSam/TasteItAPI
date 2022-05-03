package com.tatisam.tasteit.entities.app;

import lombok.*;

import javax.persistence.*;

/**
 * Class Entity for rating in application
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 02/05/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ip;
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @ToString.Exclude
    private Country country;
}
