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
package org.ressec.avocado.core.test.json;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;

/**
 * A simple org.ressec.avocado.core.test bean object.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class SimpleBean
{
    /**
     * Name.
     */
    @Getter
    private final String name;

    /**
     * Numeric value.
     */
    @Getter
    private final int numeric;

    /**
     * Time stamp.
     */
    @Getter
    private final Instant timestamp = Instant.now();

    /**
     * Key value.
     */
    @Getter
    private final long key;

    @Builder
    public SimpleBean(final @NonNull String name, final int numeric, final long key)
    {
        this.name = name;
        this.numeric = numeric;
        this.key = key;
    }
}
