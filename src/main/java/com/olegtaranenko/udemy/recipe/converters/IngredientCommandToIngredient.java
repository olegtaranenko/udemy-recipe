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
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient>{

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }
        return Ingredient.builder()
                .id(source.getId())
                .description(source.getDescription())
                .uom(uomConverter.convert(source.getUom()))
                .amount(source.getAmount())
                .build();
    }
}
