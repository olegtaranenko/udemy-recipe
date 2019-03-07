package com.olegtaranenko.udemy.recipe.controllers;

import com.olegtaranenko.udemy.recipe.domain.Recipe;
import com.olegtaranenko.udemy.recipe.services.RecipeService;
import com.olegtaranenko.udemy.recipe.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    private IndexController indexController;



    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {

        // given
        Set<Recipe> recipes = new HashSet<>();
        Recipe m2 = new Recipe();
        m2.setId(2L);
        recipes.add(m2);
        Recipe m3 = new Recipe();
        m3.setId(3L);
        recipes.add(m3);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);

        // then
        assertEquals("index", viewName);

        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}