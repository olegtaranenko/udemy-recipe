package com.olegtaranenko.udemy.recipe.controllers;

import com.olegtaranenko.udemy.recipe.domain.Recipe;
import com.olegtaranenko.udemy.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    private RecipeController controller;



    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        controller = new RecipeController(recipeService);
    }

    @Test
    public void testGetRecipe() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Recipe recipe = Recipe.builder().id(1L).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }
}