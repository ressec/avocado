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
package org.ressec.avocado.core.junit;

import com.github.javafaker.Faker;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.Random;

/**
 * An abstract class for unit testing that provides some additional functionalities.
 * <hr>
 * <ul>
 * <li>a random generator</li>
 * <li>a faker generator</li>
 * <li>a temporary folder for storing files on the file system</li>
 * </ul>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j2
public abstract class AbstractBaseUnitTest
{
    @TempDir
    protected static Path sharedTempDirectory;

    /**
     * System specific file separator character.
     */
    protected static String fileSeparator = System.getProperty("file.separator");

    /**
     * Random number generator.
     */
    protected final Random random = new Random();

    /**
     * Fake Data generator.
     */
    protected final Faker faker = new Faker();

    /**
     * Returns the org.ressec.avocado.core.test folder name.
     * @return Test folder name.
     */
    public static String getTestFolderName()
    {
        return sharedTempDirectory.toString();
    }

    /**
     * Returns the org.ressec.avocado.core.test folder path.
     * @return Test folder path.
     */
    public static Path getTestFolderPath()
    {
        return sharedTempDirectory;
    }

    /**
     * Returns the org.ressec.avocado.core.test folder file.
     * @return Test folder file.
     */
    public static File getTestFolderFile()
    {
        return sharedTempDirectory.toFile();
    }

    /**
     * Checks if the given file name exist in the org.ressec.avocado.core.test folder?
     * @param filename File name to check.
     * @return True if the file exist, false otherwise.
     */
    protected static boolean existFile(final @NonNull String filename)
    {
        File file = new File(filename);
        return file.exists();
    }

    /**
     * Initializes the org.ressec.avocado.core.test case.
     */
    @BeforeAll
    static void setUpBeforeClass()
    {
        LOGGER.info(String.format("Test folder available at: [%s]", sharedTempDirectory.getFileName()));
    }

    /**
     * Finalizes the org.ressec.avocado.core.test case.
     */
    @AfterAll
    public static void tearDownAfterClass()
    {
        LOGGER.info(String.format("Test folder at: [%s] deleted!", sharedTempDirectory.getFileName()));
    }

    /**
     * Sets up the org.ressec.avocado.core.test case.
     */
    @BeforeEach
    protected void setUpBeforeEach()
    {
        // Empty
    }

    /**
     * Tears down the org.ressec.avocado.core.test case.
     */
    @AfterEach
    protected void tearDownAfterEach()
    {
        // Empty
    }
}
