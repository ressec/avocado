/*
 * Copyright(c) 2021 by Resse Christophe.
 * --------------------------------------------------------------------------------------
 * This file is part of Resse Christophe public projects which is licensed
 * under the Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 *
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * --------------------------------------------------------------------------------------
 */
package org.ressec.avocado.core.test.random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ressec.avocado.core.exception.unchecked.EnumerationException;
import org.ressec.avocado.core.junit.AbstractBaseUnitTest;
import org.ressec.avocado.core.random.EnumRandomGenerator;

import java.util.List;

/**
 * A class for unit testing the {@link EnumRandomGenerator} entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
final class TestEnumRandomGenerator extends AbstractBaseUnitTest
{
    /**
     * Sets up the org.ressec.avocado.core.test case.
     */
    @BeforeEach
    protected void setUpBeforeEach()
    {
        // Empty
    }

    /**
     * Tears down the org.ressec.avocado.core.test case.
     */
    @AfterEach
    protected void tearDownAfterEach()
    {
        // Empty
    }

    /**
     * Test the {@link EnumRandomGenerator#exclude(Enum)} service.
     */
    @Test
    final void testEnumRandomGeneratorExcludeEnumeratedValue()
    {
        EnumRandomGenerator generator = new EnumRandomGenerator(NaturalNumberType.class).exclude(NaturalNumberType.ONE);

        List<Enum<?>> values = generator.genMany(100);

        Assertions.assertNotNull(values);
        Assertions.assertEquals(100, values.size());

        for (Enum<?> value : values)
        {
            Assertions.assertNotEquals(NaturalNumberType.ONE, value);
        }
    }

    /**
     * Test the {@link EnumRandomGenerator#exclude(Enum)} service raising an exception due to an excluded enumerated
     * value.
     */
    @Test()
    final void testEnumRandomGeneratorExcludeEnumeratedValueWithException()
    {
        EnumRandomGenerator generator = new EnumRandomGenerator(NaturalNumberType.class)
                .exclude(NaturalNumberType.ONE)
                .exclude(NaturalNumberType.TWO)
                .exclude(NaturalNumberType.THREE)
                .exclude(NaturalNumberType.FOUR)
                .exclude(NaturalNumberType.FIVE)
                .exclude(NaturalNumberType.SIX)
                .exclude(NaturalNumberType.SEVEN)
                .exclude(NaturalNumberType.EIGHT)
                .exclude(NaturalNumberType.NINE)
                .exclude(NaturalNumberType.TEN);

        Assertions.assertThrows(EnumerationException.class, () ->
        {
            List<Enum<?>> values = generator.genMany(100);
        });
    }
}
