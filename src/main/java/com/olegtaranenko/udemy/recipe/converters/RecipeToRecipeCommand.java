package com.olegtaranenko.udemy.recipe.converters;

import com.olegtaranenko.udemy.recipe.commands.RecipeCommand;
import com.olegtaranenko.udemy.recipe.domain.Category;
import com.olegtaranenko.udemy.recipe.domain.Ingredient;
import com.olegtaranenko.udemy.recipe.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{
    
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;


    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand command = RecipeCommand.builder()
                .id(source.getId())
                .description(source.getDescription())
                .cookTime(source.getCookTime())
                .prepareTime(source.getPrepareTime())
                .difficulty(source.getDifficulty())
                .directions(source.getDirections())
                .serving(source.getServing())
                .url(source.getUrl())
                .image(source.getImage())
                .source(source.getSource())
                .notes(notesConverter.convert(source.getNotes()))
                .build();
        
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConverter.convert(category)));
        }
        
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach((Ingredient ingredient) -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        
        return command;
    }

}
