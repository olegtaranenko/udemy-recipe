package com.olegtaranenko.udemy.recipe.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void before() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long id = 42L;

        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}