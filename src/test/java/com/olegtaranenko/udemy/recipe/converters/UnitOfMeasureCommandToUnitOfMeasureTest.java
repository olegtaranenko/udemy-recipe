package com.olegtaranenko.udemy.recipe.converters;

import com.olegtaranenko.udemy.recipe.commands.UnitOfMeasureCommand;
import com.olegtaranenko.udemy.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "DESCRIPTION";

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        // given
        UnitOfMeasureCommand command = UnitOfMeasureCommand.builder().id(LONG_VALUE).description(DESCRIPTION).build();

        UnitOfMeasure uom = converter.convert(command);

        // then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}