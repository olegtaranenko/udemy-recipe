package com.olegtaranenko.udemy.recipe.services;

import com.olegtaranenko.udemy.recipe.commands.RecipeCommand;
import com.olegtaranenko.udemy.recipe.converters.RecipeCommandToRecipe;
import com.olegtaranenko.udemy.recipe.converters.RecipeToRecipeCommand;
import com.olegtaranenko.udemy.recipe.domain.Recipe;
import com.olegtaranenko.udemy.recipe.exceptions.NotFoundException;
import com.olegtaranenko.udemy.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getRecipesTest() {
        Recipe recipe = new Recipe();

        HashSet<Recipe> recipesData = new HashSet<Recipe>();

        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        Mockito.verify(recipeRepository, times(1)).findAll();
    }


    @Test
    public void getRecipeCoomandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception {
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe returnedRecipe = recipeService.findById(1L);
    }

    @Test
    public void findByIdTest() {
        Long ID = 1L;
        Optional<Recipe> recipe = Optional.of(Recipe.builder().id(ID).build());

        when(recipeRepository.findById(any())).thenReturn(recipe);

        Recipe found = recipeService.findById(ID);

        assertNotNull(found);

        assertEquals(ID, found.getId());

        verify(recipeRepository, times(1)).findById(ID);
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void deleteByIdTest() throws Exception {

        // given
        Long idToDelete = 2L;

        // when
        recipeRepository.deleteById(idToDelete);

        // no "when()", since method has void return type

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}