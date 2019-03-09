package com.olegtaranenko.udemy.recipe.converters;

import com.olegtaranenko.udemy.recipe.commands.UnitOfMeasureCommand;
import com.olegtaranenko.udemy.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{


    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }
        return UnitOfMeasure.builder().id(source.getId()).description(source.getDescription()).build();
    }
}
