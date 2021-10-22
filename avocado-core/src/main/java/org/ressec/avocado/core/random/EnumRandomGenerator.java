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
package org.ressec.avocado.core.random;

import lombok.NonNull;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;
import org.ressec.avocado.core.exception.unchecked.EnumerationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration random generator used to randomly generate enumerated values based on a given enumeration class.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Facade(accessor = "enum")
public final class EnumRandomGenerator extends Generator<Enum<?>>
{
    /**
     * Enumeration class.
     */
    private final Class<? extends Enum<?>> enumClass;

    /**
     * Random number generator.
     */
    private final NaturalGenerator generator;

    /**
     * List of excluded values for a random generation.
     */
    private List<Enum<?>> excludes;

    /**
     * Creates a new enumeration generator.
     * @param enumClass Enumeration class.
     */
    public EnumRandomGenerator(final @NonNull Class<? extends Enum<?>> enumClass)
    {
        this.enumClass = enumClass;
        this.generator = new NaturalGenerator().min(0).max(enumClass.getEnumConstants().length - 1);
    }

    /**
     * Excludes a value from the enumerated values to be randomly generated.
     * @param value Value to exclude from random generation.
     * @return {@link EnumRandomGenerator}.
     */
    public EnumRandomGenerator exclude(final @NonNull Enum<?> value)
    {
        if (excludes == null)
        {
            excludes = new ArrayList<>();
        }

        if (!excludes.contains(value))
        {
            excludes.add(value);
        }

        return this;
    }

    /**
     * Generates a random enumerated value.
     * @return Random enumerated value.
     */
    @Override
    public Enum<?> gen()
    {
        Enum<?> value;

        if (excludes != null && !excludes.isEmpty())
        {
            if (enumClass.getEnumConstants().length == excludes.size())
            {
                throw new EnumerationException(String.format(
                        "Not able to generate a random enumerated value for enumeration: '%s' as all values have been explicitly excluded!",
                        enumClass.getSimpleName()));
            }

            do
            {
                value = enumClass.getEnumConstants()[generator.gen()];
                if (!excludes.contains(value))
                {
                    return value;
                }
            }
            while (true);
        }

        return enumClass.getEnumConstants()[generator.gen()];
    }
}
