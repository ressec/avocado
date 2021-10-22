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

import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ressec.avocado.core.helper.JsonHelper;
import org.ressec.avocado.core.junit.AbstractBaseUnitTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * A org.ressec.avocado.core.test class for unit testing the {@link JsonHelper} entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j2
class TestJsonHelper extends AbstractBaseUnitTest
{
    /**
     * Returns a random number given an upper bound.
     * @param bound Upper bound.
     * @return Random generated number.
     */
    private int getRandomNumber(final int bound)
    {
        return random.nextInt(bound);
    }

    /**
     * Generates a new simple bean instance.
     */
    private SimpleBean generateBean()
    {
        return SimpleBean.builder()
                .name(faker.name().firstName())
                .key(getRandomNumber(Integer.MAX_VALUE))
                .numeric(getRandomNumber(Integer.MAX_VALUE))
                .build();
    }

    private File getFile()
    {
        return new File(sharedTempDirectory + UUID.randomUUID().toString() + ".json");
    }

    /**
     * Test the serialization of an object instance in a {@code json} file.
     */
    @Test
    void testJsonHelperSerializeObject() throws IOException
    {
        // Create a temporary file to serialize the bean in json format.
        //final File file = Files.createFile(TEST_FOLDER, "so.json");
        SimpleBean object = generateBean();
        JsonHelper.serialize(getFile(), object);
    }

    /**
     * Test the de-serialization of a {@code json} file into a simple bean.
     */
    @Test
    void testJsonHelperDeserializeObject() throws IOException
    {
        SimpleBean origin;
        SimpleBean target;

        // Create a temporary file to serialize the bean in json format.
        // final File file = temporaryFolder.newFile("so.json");
        File file = getFile();
        origin = generateBean();

        JsonHelper.serialize(file, origin);
        target = JsonHelper.deserialize(file, SimpleBean.class);

        Assertions.assertNotNull(target);
    }

    /**
     * Test the serialization of a list of simple beans in a {@code json} file.
     */
    @Test
    void testJsonHelperSerializeList() throws IOException
    {
        List<SimpleBean> list = new ArrayList<>();

        // Create a temporary file to serialize the bean in json format.
        //final File file = temporaryFolder.newFile("so.json");
        File file = getFile();

        int count = 1 + getRandomNumber(99);

        for (int i = 0; i < count; i++)
        {
            list.add(generateBean());
        }

        Type type = new TypeToken<ArrayList<SimpleBean>>(){}.getType();
        JsonHelper.serialize(file, list, type);
    }

    /**
     * Test the de-serialization of a {@code json} file into a list of simple beans.
     */
    @Test
    void testJsonHelperDeserializeList() throws IOException
    {
        List<SimpleBean> origin = new ArrayList<>();
        List<SimpleBean> target;

        // Create a temporary file to serialize the bean in json format.
        // final File file = temporaryFolder.newFile("so.json");
        File file = getFile();

        int count = 1 + getRandomNumber(99);
        for (int i = 0; i < count; i++)
        {
            origin.add(generateBean());
        }

        Type type = new TypeToken<ArrayList<SimpleBean>>(){}.getType();
        JsonHelper.serialize(file, origin, type);
        target = JsonHelper.deserialize(file, type);

        Assertions.assertNotNull(target);
    }

    /**
     * Test the serialization of a set of simple beans in a {@code json} file.
     */
    @Test
    void testJsonHelperSerializeSet() throws IOException
    {
        Set<SimpleBean> set = new HashSet<>();

        // Create a temporary file to serialize the bean in json format.
        // final File file = temporaryFolder.newFile("so.json");
        File file = getFile();

        int count = 1 + getRandomNumber(99);
        for (int i = 0; i < count; i++)
        {
            set.add(generateBean());
        }

        Type type = new TypeToken<HashSet<SimpleBean>>(){}.getType();
        JsonHelper.serialize(file, set, type);
    }

    /**
     * Test the de-serialization of a {@code json} file into a set of simple beans.
     */
    @Test
    void testJsonHelperDeserializeSet() throws IOException
    {
        Set<SimpleBean> origin = new HashSet<>();
        Set<SimpleBean> target;

        // Create a temporary file to serialize the bean in json format.
        //final File file = temporaryFolder.newFile("so.json");
        File file = getFile();

        int count = 1 + getRandomNumber(99);
        for (int i = 0; i < count; i++)
        {
            origin.add(generateBean());
        }

        Type type = new TypeToken<HashSet<SimpleBean>>(){}.getType();
        JsonHelper.serialize(file, origin, type);
        target = JsonHelper.deserialize(file, type);

        Assertions.assertNotNull(target);
    }

    /**
     * Test the serialization of a map of simple beans in a {@code json} file.
     */
    @Test
    void testJsonHelperSerializeMap() throws IOException
    {
        Map<Integer, SimpleBean> map = new HashMap<>();

        // Create a temporary file to serialize the bean in json format.
        //final File file = temporaryFolder.newFile("so.json");
        File file = getFile();

        int count = 1 + getRandomNumber(99);

        for (int i = 0; i < count; i++)
        {
            map.put(i, generateBean());

        }

        Type type = new TypeToken<HashMap<Integer, SimpleBean>>(){}.getType();
        JsonHelper.serialize(file, map, type);
    }

    /**
     * Test the de-serialization of a {@code json} file into a map of simple beans.
     */
    @Test
    void testJsonHelperDeserializeMap() throws IOException
    {
        Map<Integer, SimpleBean> origin = new HashMap<>();
        Map<Integer, SimpleBean> target;

        // Create a temporary file to serialize the bean in json format.
        //final File file = temporaryFolder.newFile("so.json");
        File file = getFile();

        int count = 1 + getRandomNumber(99);
        for (int i = 0; i < count; i++)
        {
            origin.put(i, generateBean());
        }

        Type type = new TypeToken<HashMap<Integer, SimpleBean>>(){}.getType();
        JsonHelper.serialize(file, origin, type);
        target = JsonHelper.deserialize(file, type);

        Assertions.assertNotNull(target);
    }
}
