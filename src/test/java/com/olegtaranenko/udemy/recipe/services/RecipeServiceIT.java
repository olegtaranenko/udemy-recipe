package com.olegtaranenko.udemy.recipe.services;

import com.olegtaranenko.udemy.recipe.commands.RecipeCommand;
import com.olegtaranenko.udemy.recipe.converters.RecipeCommandToRecipe;
import com.olegtaranenko.udemy.recipe.converters.RecipeToRecipeCommand;
import com.olegtaranenko.udemy.recipe.domain.Recipe;
import com.olegtaranenko.udemy.recipe.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;


    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {

        // given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        // when
        assertNotNull(testRecipeCommand);
        assertNotEquals(NEW_DESCRIPTION, testRecipeCommand.getDescription());
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        // then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());

    }

    @Test
    public void findByDescription() throws Exception {
        Recipe recipe = recipeService.findByDescription("Perfect Guacamole");

        assertNotNull(recipe);
        assertEquals((Long) 1L, recipe.getId());


    }


}