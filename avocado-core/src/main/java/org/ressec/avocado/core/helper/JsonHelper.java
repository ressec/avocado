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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.ressec.avocado.core.exception.checked.FileException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Utility class containing services for the {@code Gson} library.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@UtilityClass
public class JsonHelper
{
    /**
     * Serializes an object in a given json file.
     * @param file File where to serialize the content of the given object.
     * @param object Object to serialize.
     * @throws IOException Thrown in case an error occurred while serializing the object.
     */
    public static void serialize(final @NonNull File file, final @NonNull Object object) throws IOException
    {
        serialize(file.getPath(), object);
    }

    /**
     * Serializes an object in a given json file.
     * @param filePath File path where to serialize the content of the given object.
     * @param object Object to serialize.
     * @throws IOException Thrown in case an error occurred while serializing the object.
     */
    public static void serialize(final @NonNull String filePath, final @NonNull Object object) throws IOException
    {
        checkFolder(filePath);

        @Cleanup
        var writer = new FileWriter(filePath);

        var gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);

        writer.write(json);
    }

    /**
     * Serializes an object in a given json file.
     * @param gson Gson instance to use to serialize.
     * @param filePath File path where to serialize the content of the given object.
     * @param object Object to serialize.
     * @throws IOException Thrown in case an error occurred while serializing the object.
     */
    public static void serialize(final @NonNull Gson gson, final @NonNull String filePath, final @NonNull Object object) throws IOException
    {
        checkFolder(filePath);

        @Cleanup
        var writer = new FileWriter(filePath);
        String json = gson.toJson(object);
        writer.write(json);
    }

    /**
     * Serializes a collection of objects in json (Array, Set, Map, etc.).
     * @param file File representing the json file.
     * @param collection Collection of objects to serialize.
     * @param type Type of the collection (including the type of object stored in the collection).
     * @throws IOException Thrown in case an error occurred while serializing the collection.
     */
    public static void serialize(final @NonNull File file, final @NonNull Object collection, final @NonNull Type type) throws IOException
    {
        serialize(file.getPath(), collection, type);
    }

    /**
     * Serializes a collection of objects in json (Array, Set, Map, etc.).
     * @param gson Gson instance to use to serialize.
     * @param file File representing the json file.
     * @param collection Collection of objects to serialize.
     * @param type Type of the collection (including the type of object stored in the collection).
     * @throws IOException Thrown in case an error occurred while serializing the collection.
     */
    public static void serialize(final @NonNull Gson gson, final @NonNull File file, final @NonNull Object collection, final @NonNull Type type) throws IOException
    {
        serialize(gson,file.getPath(), collection, type);
    }

    /**
     * Serializes a collection of objects in json (Array, Set, Map, etc.).
     * @param filePath File path of the json file.
     * @param collection Collection of objects to serialize.
     * @param type Type of the collection (including the type of object stored in the collection).
     * @throws IOException Thrown in case an error occurred while serializing the collection.
     */
    public static void serialize(final @NonNull String filePath, final @NonNull Object collection, final @NonNull Type type) throws IOException
    {
        checkFolder(filePath);

        var gson = new GsonBuilder().setPrettyPrinting().create();

        @Cleanup
        var writer = new FileWriter(filePath);
        String json = gson.toJson(collection, type);
        writer.write(json);
    }

    /**
     * Serializes a collection of objects in json (Array, Set, Map, etc.).
     * @param gson Gson instance to use to serialize.
     * @param filePath File path of the json file.
     * @param collection Collection of objects to serialize.
     * @param type Type of the collection (including the type of object stored in the collection).
     * @throws IOException Thrown in case an error occurred while serializing the collection.
     */
    public static void serialize(final @NonNull Gson gson, final @NonNull String filePath, final @NonNull Object collection, final @NonNull Type type) throws IOException
    {
        checkFolder(filePath);

        @Cleanup
        var writer = new FileWriter(filePath);
        String json = gson.toJson(collection, type);
        writer.write(json);
    }

    /**
     * De-serializes a collection of objects from a json file (Array, Set, Map, etc.).
     * @param jsonString String containing the json elements.
     * @param type Type of the collection (including the type of object stored in the collection).
     * @param <T> Type of the collection of de-serialized objects.
     * @return De-serialized collection of objects.
     */
    public static <T> T deserialize(final @NonNull String jsonString, final @NonNull Type type)
    {
        return new Gson().fromJson(jsonString, type);
    }

    /**
     * De-serializes a collection of objects from a json file (Array, Set, Map, etc.).
     * @param gson Gson object.
     * @param jsonString String containing the json elements.
     * @param type Type of the collection (including the type of object stored in the collection).
     * @param <T> Type of the collection of de-serialized objects.
     * @return De-serialized collection of objects.
     */
    public static <T> T deserialize(final @NonNull Gson gson, final @NonNull String jsonString, final @NonNull Type type)
    {
        return gson.fromJson(jsonString, type);
    }

    /**
     * De-serializes a collection of objects from a json file (Array, Set, Map, etc.).
     * @param file File representing the json file.
     * @param type Type of the collection (including the type of object stored in the collection).
     * @param <T> Type of the collection of de-serialized objects.
     * @return De-serialized collection of objects.
     * @throws IOException Thrown in case an error occurred while de-serializing the collection.
     */
    public static <T> T deserialize(final @NonNull File file, final @NonNull Type type) throws IOException
    {
        return new Gson().fromJson(new FileReader(file), type);
    }

    /**
     * De-serializes a collection of objects from a json file (Array, Set, Map, etc.).
     * @param gson Gson object.
     * @param file Json file.
     * @param type Type of the collection (including the type of object stored in the collection).
     * @param <T> Type of the collection of de-serialized objects.
     * @return De-serialized collection of objects.
     * @throws IOException Thrown in case an error occurred while de-serializing the collection.
     */
    public static <T> T deserialize(final @NonNull Gson gson, final @NonNull File file, final @NonNull Type type) throws IOException
    {
        return gson.fromJson(new FileReader(file), type);
    }

    /**
     * De-serializes a given json file containing the given object class.
     * @param file File containing the object to de-serialize.
     * @param objectClass Object class to de-serialize.
     * @param <T> Type of the de-serialized object.
     * @return De-serialized object.
     * @throws IOException Thrown in case an error occurred while de-serializing the given object.
     */
    public static <T> T deserialize(final @NonNull File file, final @NonNull Class<?> objectClass) throws IOException
    {
        return new Gson().fromJson(new FileReader(file), TypeToken.get(objectClass).getType());
    }

    /**
     * Creates the folder structure (if necessary) specified in the given file.
     * @param file File.
     */
    private static void checkFolder(final @NonNull File file)
    {
        if (!file.exists())
        {
            FileHelper.createFileWithDirs(file);
        }
    }

    /**
     * Creates the folder structure (if necessary) specified in the given file path.
     * @param filePath File path.
     */
    private static void checkFolder(final @NonNull String filePath)
    {
        var file = new File(filePath);
        checkFolder(file);
    }

    /**
     * Creates a file and the necessary folder structure given a file path and name.
     * @param filePath File path and name.
     * @return File.
     * @throws FileException An error occurred while trying to create the new file.
     */
    public static File createFile(final @NonNull String filePath) throws FileException
    {
        checkFolder(filePath);
        var file = new File(filePath);

        try
        {
            if (!file.createNewFile())
            {
                throw new FileException(String.format("Cannot create file: '%s'", filePath));
            }
        }
        catch (IOException e)
        {
            // Do nothing!
        }

        return file;
    }
}
