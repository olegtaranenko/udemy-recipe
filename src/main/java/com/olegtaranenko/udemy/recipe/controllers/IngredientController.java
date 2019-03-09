package com.olegtaranenko.udemy.recipe.controllers;

import com.olegtaranenko.udemy.recipe.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user1 at Mar 09, 2019
 */
@Slf4j
@Controller
@AllArgsConstructor
public class IngredientController {
    private final RecipeService recipeService;

    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {
        log.debug("Getting ingredient list for recipe id: " + id);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "/recipe/ingredient/list";
    }
}
