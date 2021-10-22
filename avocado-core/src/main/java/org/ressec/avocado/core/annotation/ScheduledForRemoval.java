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
 * Indicates that a public API of the annotated element (class, method or field) is subject to removal in a future
 * version. It's a stronger variant of {@link Deprecated} annotation.
 * <br>
 * Since many tools aren't aware of this annotation it should be used as an addition to {@code @Deprecated} annotation
 * or {@code @deprecated} Javadoc tag only.
 * <br>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface ScheduledForRemoval
{
    /**
     * Version of the library.
     */
    String version = "";
}
