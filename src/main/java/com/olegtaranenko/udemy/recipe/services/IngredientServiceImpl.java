package com.olegtaranenko.udemy.recipe.services;

import com.olegtaranenko.udemy.recipe.commands.IngredientCommand;
import com.olegtaranenko.udemy.recipe.converters.IngredientCommandToIngredient;
import com.olegtaranenko.udemy.recipe.converters.IngredientToIngredientCommand;
import com.olegtaranenko.udemy.recipe.domain.Ingredient;
import com.olegtaranenko.udemy.recipe.domain.Recipe;
import com.olegtaranenko.udemy.recipe.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by user1 at Mar 09, 2019
 */
@Slf4j
@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    @Override
    public IngredientCommand findCommandByRecipeIdAndId(Long recipeId, Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (optionalRecipe.isEmpty()) {
            log.error("recipe id not found. Try to find: " + recipeId);
            return null;
        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> ingredientCommand = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(ingredientToIngredientCommand::convert)
                .findFirst();

        if (ingredientCommand.isEmpty()) {
            log.error("ingredient id not found. Try to find: " + id);
            return null;
        }

        return ingredientCommand.get();
    }

}
