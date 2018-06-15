/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded;

import io.idden.nickreloaded.version.wrapper.VersionWrapper;

/**
 * The plugin constants.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class NickReloadedConstants
{
    public static String         VERSION = "2.0-rc1";
    public static String         NMS_PKG_VERSION;
    public static VersionWrapper WRAPPER;

    public void unset()
    {
        VERSION = null;
        NMS_PKG_VERSION = null;
        WRAPPER = null;
    }
}
