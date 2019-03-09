package com.olegtaranenko.udemy.recipe.converters;

import com.olegtaranenko.udemy.recipe.commands.CategoryCommand;
import com.olegtaranenko.udemy.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user1 at Mar 09, 2019
 */
public class CategoryCommandToCategoryTest {
    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "DESCRIPTION";

    private CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        // given
        CategoryCommand command = CategoryCommand.builder().id(LONG_VALUE).description(DESCRIPTION).build();

        Category uom = converter.convert(command);

        // then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}