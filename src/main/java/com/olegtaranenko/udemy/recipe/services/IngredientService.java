package com.olegtaranenko.udemy.recipe.services;

import com.olegtaranenko.udemy.recipe.commands.IngredientCommand;
import com.olegtaranenko.udemy.recipe.commands.RecipeCommand;
import com.olegtaranenko.udemy.recipe.domain.Ingredient;
import com.olegtaranenko.udemy.recipe.domain.Recipe;

import java.util.Set;

/**
 * Created by user1 at Mar 05, 2019
 */
public interface IngredientService {

//    Ingredient findByRecipeIdAndId(Long recipeId, Long id);

    IngredientCommand findCommandByRecipeIdAndId(Long recipeId, Long id);
}
