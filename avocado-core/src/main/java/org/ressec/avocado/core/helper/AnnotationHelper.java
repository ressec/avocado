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
package org.ressec.avocado.core.helper;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Helper class providing convenient services for annotation manipulations.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@UtilityClass
public class AnnotationHelper
{
    /**
     * Changes the value of an annotation property.
     * @param annotation Annotation to change.
     * @param key Key of the annotation property.
     * @param value New value of the property.
     * @return Old property value.
     */
    public static Object changeAnnotationValue(final @NonNull Annotation annotation, final @NonNull String key, final @NonNull Object value)
    {
        Field field;
        Map<String, Object> memberValues;

        Object handler = Proxy.getInvocationHandler(annotation);

        try
        {
            field = handler.getClass().getDeclaredField("memberValues");
        }
        catch (NoSuchFieldException | SecurityException e)
        {
            throw new IllegalStateException(e);
        }

        field.setAccessible(true); // TODO Find another solution!

        try
        {
            memberValues = (Map<String, Object>) field.get(handler);
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            throw new IllegalStateException(e);
        }

        Object oldValue = memberValues.get(key);
        if (oldValue == null || oldValue.getClass() != value.getClass())
        {
            throw new IllegalArgumentException();
        }

        memberValues.put(key,value);

        return oldValue;
    }
}