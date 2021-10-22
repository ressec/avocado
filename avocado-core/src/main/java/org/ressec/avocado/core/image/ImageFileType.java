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

import lombok.Getter;

/**
 * An enumeration to expose the available values for supported image format types.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum ImageFileType
{
    /**
     * Image format is PNG (see: https://en.wikipedia.org/wiki/Portable_Network_Graphics).
     */
    PNG(".png"),

    /**
     * Image format is GIF (see: https://en.wikipedia.org/wiki/GIF).
     */
    GIF(".gif"),

    /**
     * Image format is TIFF (see: https://en.wikipedia.org/wiki/TIFF).
     */
    TIFF(".tif"),

    /**
     * Image format is BMP (see: https://en.wikipedia.org/wiki/BMP_file_format).
     */
    BMP(".bmp"),

    /**
     * Image format is JPEG (see: https://en.wikipedia.org/wiki/JPEG).
     */
    JPEG(".jpg");

    /**
     * Image format extension
     */
    @Getter
    private final String extension;

    /**
     * Creates a new image format type.
     * @param extension Image format file extension.
     */
    ImageFileType(String extension)
    {
        this.extension = extension;
    }
}
