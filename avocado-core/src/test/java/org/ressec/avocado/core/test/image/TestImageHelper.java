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
package org.ressec.avocado.core.test.image;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ressec.avocado.core.image.ImageException;
import org.ressec.avocado.core.image.ImageFileType;
import org.ressec.avocado.core.image.ImageHelper;
import org.ressec.avocado.core.image.ImageScaleType;
import org.ressec.avocado.core.junit.AbstractBaseUnitTest;

import java.awt.image.BufferedImage;

/**
 * A class for unit testing the {@link ImageHelper} services entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j2
final class TestImageHelper extends AbstractBaseUnitTest
{
    /**
     * Icon path name.
     */
    private static final String ICON_PATHNAME = "icon/translate.png";

    @Test
    @DisplayName("Should load icon")
    final void shouldLoadIcon() throws ImageException
    {
        BufferedImage icon = ImageHelper.getIcon(ICON_PATHNAME);

        Assertions.assertNotNull(icon);
    }

    @Test
    @DisplayName("Should save icon as PNG in the org.ressec.avocado.core.test folder")
    final void shouldSaveIconAsPNG() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageFileType.PNG,
                getTestFolderName());

        LOGGER.debug(String.format("File saved at: '%s'", pathname));
        Assertions.assertTrue(existFile(pathname));
    }

    @Test
    @DisplayName("Should save and resize icon as PNG in the org.ressec.avocado.core.test folder")
    final void shouldSaveAndResizeIconAsPNG() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageScaleType.IMAGE_SCALE_13X13,
                ImageFileType.PNG,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));

        BufferedImage icon = ImageHelper.getIcon(pathname);
        Assertions.assertEquals(13,icon.getWidth());
        Assertions.assertEquals(13,icon.getHeight());
    }

    @Test
    @DisplayName("Should save icon as BMP in the org.ressec.avocado.core.test folder")
    final void shouldSaveIconAsBMP() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageFileType.BMP,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));
    }

    @Test
    @DisplayName("Should save and resize icon as BMP in the org.ressec.avocado.core.test folder")
    final void shouldSaveAndResizeIconAsBMP() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageScaleType.IMAGE_SCALE_32X32,
                ImageFileType.BMP,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));

        BufferedImage icon = ImageHelper.getIcon(pathname);
        Assertions.assertEquals(32,icon.getWidth());
        Assertions.assertEquals(32,icon.getHeight());
    }

    @Test
    @DisplayName("Should save icon as GIF in the org.ressec.avocado.core.test folder")
    final void shouldSaveIconAsGIF() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageFileType.GIF,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));
    }

    @Test
    @DisplayName("Should save and resize icon as GIF in the org.ressec.avocado.core.test folder")
    final void shouldSaveAndResizeIconAsGIF() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageScaleType.IMAGE_SCALE_64X64,
                ImageFileType.GIF,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));

        BufferedImage icon = ImageHelper.getIcon(pathname);
        Assertions.assertEquals(64,icon.getWidth());
        Assertions.assertEquals(64,icon.getHeight());
    }

    @Test
    @DisplayName("Should save icon as JPG in the org.ressec.avocado.core.test folder")
    final void shouldSaveIconAsJPG() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageFileType.JPEG,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));
    }

    @Test
    @DisplayName("Should save and resize icon as JPG in the org.ressec.avocado.core.test folder")
    final void shouldSaveAndResizeIconAsJPG() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageScaleType.IMAGE_SCALE_128X128,
                ImageFileType.JPEG,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));

        BufferedImage icon = ImageHelper.getIcon(pathname);
        Assertions.assertEquals(128,icon.getWidth());
        Assertions.assertEquals(128,icon.getHeight());
    }

    @Test
    @DisplayName("Should save icon as TIFF in the org.ressec.avocado.core.test folder")
    final void shouldSaveIconAsTIFF() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageFileType.TIFF,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));
    }

    @Test
    @DisplayName("Should save and resize icon as TIFF in the org.ressec.avocado.core.test folder")
    final void shouldSaveAndResizeIconAsTIFF() throws ImageException
    {
        String pathname = ImageHelper.saveIcon(
                ICON_PATHNAME,
                ImageScaleType.IMAGE_SCALE_256X256,
                ImageFileType.TIFF,
                getTestFolderName());

        Assertions.assertTrue(existFile(pathname));

        BufferedImage icon = ImageHelper.getIcon(pathname);
        Assertions.assertEquals(256,icon.getWidth());
        Assertions.assertEquals(256,icon.getHeight());
    }
}
