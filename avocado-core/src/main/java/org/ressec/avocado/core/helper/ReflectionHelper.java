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
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class providing convenient services for reflection manipulations.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@UtilityClass
public class ReflectionHelper
{
    /**
     * Finds a list of fields in a class hierarchy annotated with the given annotation.
     * @param type Class in which to start looking for annotated fields.
     * @param annotationType Annotation type.
     * @return List of annotated fields.
     */
    public static List<Field> findAnnotatedFieldsInClassHierarchy(final @NonNull Class<?> type, final @NonNull Class<? extends Annotation> annotationType)
    {
        List<Field> fields = new ArrayList<>();

        for (Field field : type.getDeclaredFields())
        {
            if (field.isAnnotationPresent(annotationType))
            {
                fields.add(field);
            }
        }

        if (type.getSuperclass() != null)
        {
            fields.addAll(findAnnotatedFieldsInClassHierarchy(type.getSuperclass(), annotationType));
        }

        return fields;
    }

    /**
     * Finds the field with the given name from the given object instance.
     * <br>
     * If necessary this method traverses the class hierarchy to find the given field.
     * @param instance Object instance in which to find the field.
     * @param name Field name.
     * @return Field if found.
     * @throws NoSuchFieldException Thrown in case no such field has been found.
     */
    public static Field findFieldInObjectInstance(final @NonNull Object instance, final @NonNull String name) throws NoSuchFieldException
    {
        return findFieldInClassHierarchy(instance.getClass(), name);
    }

    /**
     * Finds the field with the given field name from the given class type.
     * <br>
     * If necessary this method traverses the class hierarchy to find the given field.
     * @param type Class in which to find the field.
     * @param name Field name.
     * @return Field if found.
     * @throws NoSuchFieldException Thrown in case no such field has been found.
     */
    public static Field findFieldInClassHierarchy(final @NonNull Class<?> type, final @NonNull String name) throws NoSuchFieldException
    {
        for (Field field : type.getDeclaredFields())
        {
            if (field.getName().equals(name))
            {
                return field;
            }
        }

        if (type.getSuperclass() != null)
        {
            return findFieldInClassHierarchy(type.getSuperclass(), name);
        }

        throw new NoSuchFieldException(String.format("Cannot find field name: '%s' annotated with I18n annotation in hierarchy of class: '%s'", name, type.getName()));
    }
}