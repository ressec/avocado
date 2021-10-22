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
package org.ressec.avocado.core.test.random.external;

import lombok.extern.log4j.Log4j2;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.basics.DecimalGenerator;
import me.xdrop.jrand.generators.basics.FloatGeneratorGen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ressec.avocado.core.junit.AbstractBaseUnitTest;

import java.util.List;

/**
 * A org.ressec.avocado.core.test class for unit testing the {@code jRand} library.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j2
class TestJRand extends AbstractBaseUnitTest
{
    @BeforeEach
    public void setUpBeforeEach()
    {
        // Empty.
    }

    @AfterEach
    public void tearDownAfterEach()
    {
        // Empty.
    }

    /**
     * Test the JRand library to generate a list of float values in a given range.
     */
    @Test
    void testJRandGenerateRandomFloatValuesInRange()
    {
        FloatGeneratorGen generator = JRand.flt();
        List<Float> list = generator.range(2.478f, 2.589f).genMany(100);

        for (Float value : list)
        {
            //System.out.println(value);
            Assertions.assertTrue(value >= 2.478);
            Assertions.assertTrue(value <= 2.589);
        }
    }

    /**
     * Test the JRand library to generate a list of decimal values in a given range.
     */
    @Test
    void testJRandGenerateRandomDecimalValuesInRange()
    {
        DecimalGenerator generator = JRand.decimal();
        List<String> list = generator.digits(3).range(2.478, 2.589).genMany(100);

        for (String value : list)
        {
            //System.out.println(value);
            Assertions.assertTrue(Double.parseDouble(value) >= 2.478);
            Assertions.assertTrue(Double.parseDouble(value) <= 2.589);
        }
    }
}
