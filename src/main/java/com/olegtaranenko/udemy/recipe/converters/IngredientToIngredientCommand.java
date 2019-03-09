package com.olegtaranenko.udemy.recipe.converters;

import com.olegtaranenko.udemy.recipe.commands.IngredientCommand;
import com.olegtaranenko.udemy.recipe.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand>{

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }

        IngredientCommand ret = IngredientCommand.builder()
                .id(source.getId())
                .description(source.getDescription())
                .uom(uomConverter.convert(source.getUom()))
                .amount(source.getAmount())
                .build();

        if (source.getRecipe() != null) {
            ret.setRecipeId(source.getRecipe().getId());
        }

        return ret;
    }

}
