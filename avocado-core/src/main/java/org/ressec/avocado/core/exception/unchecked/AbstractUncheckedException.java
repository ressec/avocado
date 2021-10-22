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
package org.ressec.avocado.core.exception.unchecked;

/**
 * An abstract implementation of an unchecked exception.
 * <br><br>
 * Unchecked runtime exceptions represent conditions that, generally speaking, reflect errors in program's logic and
 * cannot be reasonably recovered from at run time. Unchecked exceptions are subclasses of RuntimeException,
 * and are usually implemented using InvalidArgumentException, NullPointerException, or
 * IllegalStateException a method is not obliged to establish a policy for the unchecked exceptions thrown by
 * its implementation (and they almost always do not do so).
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractUncheckedException extends RuntimeException
{
    /**
     * Default serialization identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new unchecked exception based on a parent exception.
     *
     * @param exception Parent exception.
     */
    protected AbstractUncheckedException(final Exception exception)
    {
        super(exception);
    }

    /**
     * Creates a new unchecked exception based on a message.
     *
     * @param message Message of the exception.
     */
    protected AbstractUncheckedException(final String message)
    {
        super(message);
    }

    /**
     * Creates a new unchecked exception based on a message and a parent exception.
     *
     * @param message   Message of the exception.
     * @param exception Parent exception.
     */
    protected AbstractUncheckedException(final String message, final Exception exception)
    {
        super(message, exception);
    }
}
