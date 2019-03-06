package com.olegtaranenko.udemy.recipe.domain;

import lombok.*;

import javax.persistence.*;

/*
    Created by user1 at Mar 05, 2019
*/
@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
