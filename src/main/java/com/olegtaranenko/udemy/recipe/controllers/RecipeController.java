package com.olegtaranenko.udemy.recipe.controllers;

import com.olegtaranenko.udemy.recipe.commands.RecipeCommand;
import com.olegtaranenko.udemy.recipe.domain.Recipe;
import com.olegtaranenko.udemy.recipe.exceptions.NotFoundException;
import com.olegtaranenko.udemy.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static java.lang.Long.lowestOneBit;
import static java.lang.Long.parseLong;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String getIndexPage(@PathVariable String id, Model model) {

        log.debug("Get recipe, id: " + id);

        model.addAttribute("recipe", recipeService.findById(parseLong(id)));

        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand saveeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + saveeCommand.getId() + "/show";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String delete(@PathVariable String id) {
        log.debug("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("exception", e);

        modelAndView.setViewName("404error");

        return modelAndView;
    }
}
