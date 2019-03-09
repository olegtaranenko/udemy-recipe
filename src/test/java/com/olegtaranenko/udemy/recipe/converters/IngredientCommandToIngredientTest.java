package com.olegtaranenko.udemy.recipe.converters;

import com.olegtaranenko.udemy.recipe.commands.IngredientCommand;
import com.olegtaranenko.udemy.recipe.commands.UnitOfMeasureCommand;
import com.olegtaranenko.udemy.recipe.domain.Ingredient;
import com.olegtaranenko.udemy.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by user1 at Mar 09, 2019
 */
public class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long ID_VALUE = 1L;
    private static final Long UOM_ID = 2L;

    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        IngredientCommand command = IngredientCommand.builder()
                .id(ID_VALUE)
                .amount(AMOUNT)
                .description(DESCRIPTION)
                .uom(UnitOfMeasureCommand.builder().id(UOM_ID).build())
                .build();

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientCommand command = IngredientCommand.builder()
                .id(ID_VALUE)
                .amount(AMOUNT)
                .description(DESCRIPTION)
                .uom(UnitOfMeasureCommand.builder()
                        .id(UOM_ID)
                        .build())
                .build();


        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }
}