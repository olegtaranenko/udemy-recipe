package com.olegtaranenko.udemy.recipe.controllers;

import com.olegtaranenko.udemy.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.Long.parseLong;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String getIndexPage(@PathVariable String id, Model model) {

        log.debug("Get recipe, id: " + id);

        model.addAttribute("recipe", recipeService.findById(parseLong(id)));

        return "recipe/show";
    }
}
