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
package org.ressec.avocado.core.annotation;

import java.lang.annotation.*;

/**
 * Indicates that the annotated element firstly appeared in the specified version of the library, so the code using
 * that element won't be compatible with older versions of the library. This information may be used by IDEs and static
 * analysis tools.
 * This annotation can be used instead of '@since' Javadoc tag if it's needed to keep that information in *.class files
 * or if you need to generate them automatically.
 * <br>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface AvailableSince
{
    /**
     * Version of the library.
     */
    String version = "";
}
