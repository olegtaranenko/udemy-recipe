package com.olegtaranenko.udemy.recipe.services;

import com.olegtaranenko.udemy.recipe.domain.Recipe;

import java.util.Set;

/**
 * Created by user1 at Mar 05, 2019
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
