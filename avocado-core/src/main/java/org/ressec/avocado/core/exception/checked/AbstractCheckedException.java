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
package org.ressec.avocado.core.exception.checked;

import lombok.NonNull;

import java.io.Serializable;

/**
 * An abstract implementation of a checked exception.
 * <br><br>
 * A checked exception represents invalid conditions in areas outside the immediate control
 * of the program (invalid user input, database problems, network outages, absent files) are
 * subclasses of {@link Exception}.
 * <br><br>
 * A method is obliged to establish a policy for all checked exceptions thrown by its
 * implementation (either pass the checked exception further up the stack, or handle it somehow).
 * <br><br>
 * It is somewhat confusing, but note as well that {@link RuntimeException} (unchecked) is itself
 * a subclass of {@link Exception} (checked).
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractCheckedException extends Exception implements Serializable
{
    /**
     * Default serialization identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new checked exception based on a parent exception.
     * @param exception Parent exception.
     */
    protected AbstractCheckedException(final @NonNull Exception exception)
    {
        super(exception);
    }

    /**
     * Creates a new checked exception based on a message.
     * @param message Message of the exception.
     */
    protected AbstractCheckedException(final @NonNull String message)
    {
        super(message);
    }

    /**
     * Creates a new checked exception based on a message and a parent exception.
     * @param message   Message of the exception.
     * @param exception Parent exception.
     */
    protected AbstractCheckedException(final String message, final Exception exception)
    {
        super(message + exception.getMessage(), exception);
    }
}

