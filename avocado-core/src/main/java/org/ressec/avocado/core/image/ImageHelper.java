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
package org.ressec.avocado.core.image;

import com.twelvemonkeys.image.ResampleOp;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;
import org.ressec.avocado.core.exception.checked.FileException;
import org.ressec.avocado.core.helper.FileHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Helper class containing services for image manipulations through the {@code ImgScalr} library.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@UtilityClass
public final class ImageHelper
{
    static
    {
        ImageIO.scanForPlugins();
    }

    /**
     * Saves an icon image.
     * @param iconName Path and file name of the source icon.
     * @throws ImageException Thrown in case an error occurred while manipulating an image.
     * @return Path and file name of the saved image.
     */
    public static String saveIcon(final @NonNull String iconName) throws ImageException
    {
        return saveIcon(
                iconName,
                ImageScaleType.IMAGE_SCALE_DEFAULT,
                ImageFileType.PNG,
                System.getProperty("java.temp.dir"),
                UUID.randomUUID().toString());
    }

    /**
     * Saves an icon image.
     * @param iconName Path and file name of the source icon.
     * @param outputType Image output type.
     * @param targetPath Destination target path (can be relative).
     * @param targetName Destination target name.
     * @throws ImageException Thrown in case an error occurred while manipulating an image.
     * @return Full pathname (containing the file extension) of the saved image file.
     */
    public static String saveIcon(final @NonNull String iconName, final ImageFileType outputType, final @NonNull String targetPath, final @NonNull String targetName) throws ImageException
    {
        return saveIcon(
                iconName,
                ImageScaleType.IMAGE_SCALE_DEFAULT,
                outputType,
                targetPath,
                targetName);
    }

    /**
     * Saves an icon image.
     * @param iconName Path and file name of the source icon.
     * @param outputType Image output type.
     * @param targetPath Destination target path (can be relative).
     * @throws ImageException Thrown in case an error occurred while manipulating an image.
     * @return Full pathname (containing the file extension) of the saved image file.
     */
    public static String saveIcon(final @NonNull String iconName, final ImageFileType outputType, final @NonNull String targetPath) throws ImageException
    {
        String filename = getFilenameWithoutExtension(iconName);

        return saveIcon(
                iconName,
                ImageScaleType.IMAGE_SCALE_DEFAULT,
                outputType,
                targetPath,
                filename);
    }

    /**
     * Saves an icon image.
     * @param iconName Path and file name of the source icon.
     * @param scaleType Image scale.
     * @param outputType Image output type.
     * @param targetPath Destination target path (can be relative).
     * @throws ImageException Thrown in case an error occurred while manipulating an image.
     * @return Full pathname (containing the file extension) of the saved image file.
     */
    public static String saveIcon(final @NonNull String iconName, final ImageScaleType scaleType, final ImageFileType outputType, final @NonNull String targetPath) throws ImageException
    {
        String filename = getFilenameWithoutExtension(iconName);

        return saveIcon(
                iconName,
                scaleType,
                outputType,
                targetPath,
                filename);
    }

    /**
     * Saves an icon image.
     * @param iconName Path and file name of the source icon.
     * @param scaleType Image scale.
     * @param outputType Image output type.
     * @param targetPath Destination target path (can be relative).
     * @param targetName Image target name.
     * @throws ImageException Thrown in case an error occurred while manipulating an image.
     * @return Full pathname (containing the file extension) of the saved image file.
     */
    public static String saveIcon(final @NonNull String iconName, final ImageScaleType scaleType, final ImageFileType outputType, final @NonNull String targetPath, final @NonNull String targetName) throws ImageException
    {
        boolean result;
        BufferedImage converted = null;

        BufferedImage icon = getIcon(iconName, scaleType);

        try
        {
            ImageIO.setUseCache(false);
            result = ImageIO.write(icon, outputType.name(), new File(targetPath, targetName + outputType.getExtension()));
            if (!result)
            {
                switch (outputType)
                {
                    case BMP:
                    case JPEG:
                    default:
                        converted = new BufferedImage(icon.getWidth(),icon.getHeight(), BufferedImage.TYPE_INT_RGB);
                        converted.createGraphics().drawImage(icon,0,0,Color.WHITE,null);
                        break;
                }

                result = ImageIO.write(converted, outputType.name(), new File(targetPath, targetName + outputType.getExtension()));
                if (!result)
                {
                    throw new ImageException(String.format("Cannot generate image output: '%s', from source: '%s', in format: '%s'", targetPath, iconName, outputType));
                }
            }
        }
        catch (IOException fe)
        {
            throw new ImageException(String.format("Cannot generate image output: '%s', from source: '%s', in format: '%s'", targetPath, iconName, outputType));
        }

        return targetPath + File.separator + targetName + outputType.getExtension();
    }

    /**
     * Retrieves an icon.
     * @param iconName Path and file name of the source icon.
     * @return {@link BufferedImage} containing the icon image.
     * @throws ImageException Thrown in case an error occurred while manipulating an image.
     */
    public static BufferedImage getIcon(final @NonNull String iconName) throws ImageException
    {
        return getIcon(iconName, ImageScaleType.IMAGE_SCALE_DEFAULT);
    }

    /**
     * Retrieves an icon.
     * @param iconName Path and file name of the source icon.
     * @param scaleType Icon scale type.
     * @return {@link BufferedImage} containing the icon image.
     * @throws ImageException Thrown in case an error occurred while manipulating an image.
     */
    public static BufferedImage getIcon(final String iconName, final ImageScaleType scaleType) throws ImageException
    {
        File file;
        BufferedImage icon;
        BufferedImage output;
        BufferedImageOp processor;
        var width = 32;
        var height = 32;

        try
        {
            file = FileHelper.getFile(iconName, ImageHelper.class);
            icon = ImageIO.read(file);

            switch (scaleType)
            {
                case IMAGE_SCALE_DEFAULT:
                    width = icon.getWidth();
                    height = icon.getHeight();
                    break;

                case IMAGE_SCALE_CUSTOM:
                    break;

                case IMAGE_SCALE_13X13:
                    width = 13;
                    height = 13;
                    break;

                case IMAGE_SCALE_16X16:
                    width = 16;
                    height = 16;
                    break;

                default:
                case IMAGE_SCALE_32X32:
                    width = 32;
                    height = 32;
                    break;

                case IMAGE_SCALE_64X64:
                    width = 64;
                    height = 64;
                    break;

                case IMAGE_SCALE_128X128:
                    width = 128;
                    height = 128;
                    break;

                case IMAGE_SCALE_256X256:
                    width = 256;
                    height = 256;
                    break;
            }

            processor = new ResampleOp(width,height, ResampleOp.FILTER_LANCZOS); // A good default filter, see class documentation for more info
            output = processor.filter(icon, null);
        }
        catch (IOException ioe)
        {
            throw new ImageException(String.format("Cannot convert icon image: '%s' due to: '%s'", iconName, ioe.getMessage()));
        }
        catch (NullPointerException | FileException e)
        {
            throw new ImageException(String.format("Cannot find icon image: '%s'", iconName));
        }

        return output;
    }

    /**
     * Returns the filename contained in the given path name.
     * @param pathname Path name.
     * @return Filename.
     */
    public static String getFilename(final @NonNull String pathname)
    {
        return FilenameUtils.getName(pathname);
    }

    /**
     * Returns the file name (without the '.' character) contained in the given path name.
     * @param pathname Path name.
     * @return Filename with its file extension.
     */
    public static String getFilenameWithoutExtension(final @NonNull String pathname)
    {
        String name = pathname;

        if (name.contains(String.valueOf(File.separatorChar)))
        {
            name = getFilename(name);
        }

        return FilenameUtils.removeExtension(name);
    }

    /**
     * Returns the image file extension of the file contained in the given path name.
     * @param pathname Path name.
     * @return {@link ImageFileType} representing the file extension of the file contained in the given path name.
     */
    public static ImageFileType getExtension(final @NonNull String pathname)
    {
        return ImageFileType.valueOf(FilenameUtils.getExtension(pathname).toUpperCase());
    }
}
