/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.core;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.NickReloadedConstants;
import io.idden.nickreloaded.addon.AddonManager;
import io.idden.nickreloaded.command.NickCommand;
import io.idden.nickreloaded.configuration.ConfigurationManager;
import io.idden.nickreloaded.listener.ListenerManager;
import io.idden.nickreloaded.logger.Logger;
import io.idden.nickreloaded.version.NMSVersion;
import org.bukkit.Bukkit;

/**
 * Manage the plugin enabling and disabling.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Manager
{
    public AddonManager         addonManager         = new AddonManager();
    public ListenerManager      listenerManager      = new ListenerManager();
    public ConfigurationManager configurationManager = new ConfigurationManager();
    public Logger               logger               = new Logger();

    private NickReloadedConstants nickReloadedConstants = new NickReloadedConstants();

    public void start()
    {
        logger.log(Logger.Level.PLUGIN, "Loading NickReloaded v" + NickReloadedConstants.VERSION + "...");

        /*
          This is important to call this as
          soon as the plugin is enabled
          otherwise the plugin can't work,
          as he depends on NMS version to work.
         */
        NMSVersion version = NMSVersion.of(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]);

        if (version.getPackage() == null)
        {
            //CraftBukkit version isn't supported by NickReloaded.
            logger.log(Logger.Level.FATAL, "Your version is incompatible with NickReloaded ! Disabling...");
            Bukkit.getPluginManager().disablePlugin(NickReloaded.INSTANCE);
        }
        else
        {
            //CraftBukkit version is supported by NickReloaded
            NickReloadedConstants.NMS_PKG_VERSION = version.getPackage();
            NickReloadedConstants.WRAPPER = version.getWrapper();

            logger.log("Wrapper", "Loaded Wrapper for version " + NickReloadedConstants.NMS_PKG_VERSION + " !");

            /*
              Load addons before everything to
              avoid dependency errors.
            */
            addonManager.loadAddons();
            new NickCommand();
            configurationManager.loadConfigurations();
            listenerManager.registerListeners();
        }

        logger.log(Logger.Level.PLUGIN, "NickReloaded v" + NickReloadedConstants.VERSION + " enabled !");
    }

    public void stop()
    {
        addonManager.unloadAddons();
        listenerManager.unregisterListeners();
        configurationManager.saveConfigurations();
        nickReloadedConstants.unset();
        logger.log(Logger.Level.PLUGIN, "Plugin disabled. Bye !");
    }
}
