package com.olegtaranenko.udemy.recipe.repositories;

import com.olegtaranenko.udemy.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by user1 at Mar 05, 2019
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findByDescription(String pattern);
}
