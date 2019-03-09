package com.olegtaranenko.udemy.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user1 at Mar 05, 2019
 */
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    @Builder.Default
    private Set<Recipe> recipes = new HashSet<>();

}
