/*
 *  MIT License
 *
 *  Copyright (c) 2017-2018 Antoine "Idden" ROCHAS
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.idden.nickreloaded.core;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.addon.AddonManager;
import io.idden.nickreloaded.listener.ListenerManager;
import io.idden.nickreloaded.logger.Logger;
import io.idden.nickreloaded.version.NMSVersion;
import io.idden.nickreloaded.version.wrapper.VersionWrapper;
import org.bukkit.Bukkit;

/**
 * Called on {@link NickReloaded#onEnable()}.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Manager
{
    public static VersionWrapper WRAPPER         = null;
    public static String         NMS_PKG_VERSION = null;

    public AddonManager    addonManager    = new AddonManager();
    public ListenerManager listenerManager = new ListenerManager();
    public Logger          logger          = new Logger();

    public void start()
    {
        logger.log(Logger.Level.LOG, "Loading NickReloaded v" + NickReloaded.VERSION + "...");

        /*
          This is important to call this as
          soon as the plugin is enabled
          otherwise the plugin can't work,
          as he depends on NMS version to work.
         */
        NMSVersion version = NMSVersion.of(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]);

        if (version.getPackage() == null)
        {
            logger.log(Logger.Level.FATAL, "Your version is incompatible with NickReloaded ! Disabling...");
            Bukkit.getPluginManager().disablePlugin(NickReloaded.INSTANCE);
        }
        else
        {
            NMS_PKG_VERSION = version.getPackage();
            WRAPPER = version.getWrapper();

            logger.log(Logger.Level.LOG, "Loaded Wrapper for version " + NMS_PKG_VERSION + " !");

            /*
              Load addons before everything to
              avoid dependency errors.
            */
            addonManager.loadAddons();
        }

        listenerManager.registerListeners();

        logger.log(Logger.Level.LOG, "NickReloaded v" + NickReloaded.VERSION + " enabled !");
    }

    public void stop()
    {
        addonManager.unloadAddons();
        listenerManager.unregisterListeners();
        logger.log(Logger.Level.LOG, "Plugin disabled. Bye !");
        WRAPPER = null;
    }
}
