package com.olegtaranenko.udemy.recipe.converters;

import com.olegtaranenko.udemy.recipe.commands.NotesCommand;
import com.olegtaranenko.udemy.recipe.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{


    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }
        return NotesCommand.builder().id(source.getId()).recipeNotes(source.getRecipeNotes()).build();
    }

}
