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

/**
 * An enumeration to expose the available values for the icon scale types.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum ImageScaleType
{
    /**
     * Use the original image width and height.
     */
    IMAGE_SCALE_DEFAULT,

    /**
     * Use a custom width and height to resize the image.
     */
    IMAGE_SCALE_CUSTOM,

    /**
     * Resize image to 13x13 pixels.
     */
    IMAGE_SCALE_13X13,

    /**
     * Resize image to 16x16 pixels.
     */
    IMAGE_SCALE_16X16,

    /**
     * Resize image to 32x32 pixels.
     */
    IMAGE_SCALE_32X32,

    /**
     * Resize image to 64x64 pixels.
     */
    IMAGE_SCALE_64X64,

    /**
     * Resize image to 128x128 pixels.
     */
    IMAGE_SCALE_128X128,

    /**
     * Resize image to 256x256 pixels.
     */
    IMAGE_SCALE_256X256;
}
