package com.olegtaranenko.udemy.recipe.controllers;

import com.olegtaranenko.udemy.recipe.domain.Category;
import com.olegtaranenko.udemy.recipe.domain.UnitOfMeasure;
import com.olegtaranenko.udemy.recipe.repositories.CategoryRepository;
import com.olegtaranenko.udemy.recipe.repositories.UnitOfMeasureRepository;
import com.olegtaranenko.udemy.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipe", recipeService.getRecipes());

        return "index";
    }
}
