package com.olegtaranenko.udemy.recipe.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UnitOfMeasureCommand {
    private Long id;
    private String description;
}
