package com.tatisam.tasteit.entities.app;

import lombok.*;

import javax.persistence.*;

/**
 * Class Entity for Dish in application
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private String article;
    private String imgPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @ToString.Exclude
    private Country country;
}
