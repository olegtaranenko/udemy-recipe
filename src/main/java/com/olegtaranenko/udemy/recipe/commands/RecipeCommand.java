package com.olegtaranenko.udemy.recipe.commands;

import com.olegtaranenko.udemy.recipe.domain.Difficulty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeCommand {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepareTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer serving;
    private String source;

    @URL
    private String url;

    @NotBlank
    private String directions;

    @Builder.Default
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NotesCommand notes;
    @Builder.Default
    private Set<CategoryCommand> categories = new HashSet<>();
}
