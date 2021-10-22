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
package org.ressec.avocado.core.test.file;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ressec.avocado.core.exception.checked.FileException;
import org.ressec.avocado.core.helper.FileHelper;
import org.ressec.avocado.core.junit.AbstractBaseUnitTest;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A org.ressec.avocado.core.test class for unit testing the {@link FileHelper} services.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j2
final class FileHelperUnitTest extends AbstractBaseUnitTest
{
    /**
     * Manifest MF in jar file.
     */
    private static final String FILENAME_JAR_MANIFEST_MF = "jar:file:./src/test/resources/google-auth-library-credentials-0.16.1.jar!/META-INF/MANIFEST.MF";

    /**
     * GitHub action workflow.
     */
    private static final String FILENAME_GITHUB_ACTION_WORKFLOW = "file:../.github/workflows/codeql-analysis.yml";

    /**
     * Sets up the org.ressec.avocado.core.test case.
     */
    @BeforeEach
    public void setUpBeforeEach()
    {
        // Empty
    }

    /**
     * Tears down the org.ressec.avocado.core.test case.
     */
    @AfterEach
    public void tearDownAfterEach()
    {
        // Empty
    }

    /**
     * Dumps the content of a file.
     * @param filename File name.
     * @param file File.
     * @throws Exception Thrown in case an error occurred trying to dump the content of the file.
     */
    private static void dumpFile(final @NonNull String filename, final @NonNull File file) throws Exception
    {
        LOGGER.debug("File name is: ");
        LOGGER.debug(filename + "\n");

        LOGGER.debug("URL of file is: ");
        LOGGER.debug(file.toURI().toURL() + "\n");

        LOGGER.debug("File content is: ");

        Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8);
        List<String> list = stream.collect(Collectors.toList());
        for (String element : list)
        {
            LOGGER.debug(element);
        }
    }

    /**
     * Test the {@link FileHelper#getFile(String)} service using a file loaded on the classpath.
     */
    @Test
    void testFileHelperLoadFileFromClasspath() throws FileException
    {
        String filename = "/log4j2-test.properties";

        String content = FileHelper.loadFileContentAsString(filename);

        assertThat(content)
                .as("File content should not be null!")
                .isNotNull();

        LOGGER.debug(content);
    }

    /**
     * Test the {@link FileHelper#loadFileContentAsString(String)} service using a file retrieved on the class path.
     */
    @Test
    void testFileHelperGetFileFromClasspath() throws Exception
    {
        String filename = "/log4j2-test.properties";

        File file = FileHelper.getFile(filename);

        assertThat(file)
                .as("File should not be null!")
                .isNotNull();

        dumpFile(filename, file);
    }

    /**
     * Test the {@link FileHelper#getFile(String)} service using a file located in a jar file on the class path.
     */
    @Test
    void testFileHelperGetFileFromJar() throws Exception
    {
        String filename = "jar:file:/junit-4.13-beta-3.jar!/META-INF/MANIFEST.MF";
        filename = "/changelog.txt";
        filename = "/META-INF/MANIFEST.MF";

        File file = FileHelper.getFile(filename);

        assertThat(file)
                .as("File should not be null!")
                .isNotNull();

        dumpFile(filename, file);
    }

    /**
     * Test the {@link FileHelper#loadFileContentAsString(String)} service using a file located in a jar file on the class path.
     */
    @Test
    void testFileHelperLoadFileFromJar() throws FileException
    {
        String filename;
        filename = "/META-INF/MANIFEST.MF"; // Not unique, exist for many JAR files, first one is returned
        filename = "jar:file:/Users/christophe/.m2/junit/junit/4.13-beta-3/junit-4.13-beta-3.jar!/META-INF/MANIFEST.MF";
        filename = "/changelog.txt"; // Should be the changelog from lombok jar file.

        String content = FileHelper.loadFileContentAsString(filename);

        assertThat(content)
                .as("File content should not be null!")
                .isNotNull();

        LOGGER.debug(content);
    }

    /**
     * Test the {@link FileHelper#getFile(String)} service using a file retrieved from an http url.
     */
    @Test()
    @Timeout(30000) // 30 secs timeout
    void testFileHelperGetFileFromHttpUrl() throws Exception
    {
        String filename = "https://www.w3.org/TR/PNG/iso_8859-1.txt";

        File file = FileHelper.getFile(filename);

        assertThat(file)
                .as("File should not be null!")
                .isNotNull();

        dumpFile(filename, file);
    }

    /**
     * Test the {@link FileHelper#loadFileContentAsString(String)} service using a file loaded from an http url.
     */
    @Test()
    @Timeout(30000) // 30 secs timeout
    void testFileHelperLoadFileFromHttpUrl() throws FileException
    {
        String filename = "https://www.w3.org/TR/PNG/iso_8859-1.txt";

        String content = FileHelper.loadFileContentAsString(filename);

        assertThat(content)
                .as("File content should not be null!")
                .isNotNull();

        LOGGER.debug(content);
    }

    /**
     * Test the {@link FileHelper#getFile(String)} service using a file retrieved from a file url.
     */
    @Test
    void testFileHelperGetFileFromFileUrl() throws Exception
    {
        // This org.ressec.avocado.core.test will fail if run on remote machine such as GitLab or Travis!
        //String filename = "file:/Volumes/Technology/project/gitlab.com/hemajoo/foundation/hemajoo-foundation/foundation-utility/target/org.ressec.avocado.core.test-classes/log4j2-org.ressec.avocado.core.test.properties";

        File file = FileHelper.getFile(FILENAME_GITHUB_ACTION_WORKFLOW);

        assertThat(file)
                .as("File content should not be null!")
                .isNotNull();

        dumpFile("codeql-analysis.yml", file);
    }

    /**
     * Test the {@link FileHelper#loadFileContentAsString(String)} service using a file loaded from a file url.
     */
    @Test
    void testFileHelperLoadFileFromFileUrl() throws FileException
    {
        // This org.ressec.avocado.core.test will fail if run on remote machine such as GitLab or Travis!
        //String filename = "file:/Volumes/Technology/project/gitlab.com/hemajoo/foundation/hemajoo-foundation/foundation-utility/target/org.ressec.avocado.core.test-classes/log4j2-org.ressec.avocado.core.test.properties";

        String content = FileHelper.loadFileContentAsString(FILENAME_GITHUB_ACTION_WORKFLOW);

        assertThat(content)
                .as("File content should not be null!")
                .isNotNull();

        LOGGER.debug(content);
    }

    /**
     * Test the {@link FileHelper#getFile(String)} service using a file retrieved from a jar file url.
     */
    @Test
    void testFileHelperLoadFileFromJarUrl() throws Exception
    {
        //String filename = "jar:file:/Users/christophe/.m2/junit/junit/4.13-beta-3/junit-4.13-beta-3.jar!/META-INF/MANIFEST.MF";

        File file = FileHelper.getFile(FILENAME_JAR_MANIFEST_MF);

        assertThat(file)
                .as("File content should not be null!")
                .isNotNull();

        dumpFile("MANIFEST.MF", file);
    }

    /**
     * Test the {@link FileHelper#loadFileContentAsString(String)} service using a file loaded from a jar file url.
     */
    @Test
    void testFileHelperGetFileFromJarUrl() throws FileException
    {
        //String filename = "jar:file:/Users/christophe/.m2/junit/junit/4.13-beta-3/junit-4.13-beta-3.jar!/META-INF/MANIFEST.MF";

        String content = FileHelper.loadFileContentAsString(FILENAME_JAR_MANIFEST_MF);

        assertThat(content)
                .as("File content should not be null!")
                .isNotNull();

        LOGGER.debug(content);
    }

    /**
     * Test the ability to load a file using its file system complete path and name.
     */
    @Test
    void testFileHelperGetFileFromFileSystem() throws Exception
    {
        Path path = Files.createTempFile("test_temp_file", ".tjf");
        File file = path.toFile();
        file.deleteOnExit();
        String content = FileHelper.loadFileContentAsString(file.getPath());

        assertThat(content)
                .as("File content should not be null!")
                .isNotNull();
    }
}
