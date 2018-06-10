/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.addon.papi;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.addon.AbstractAddon;
import io.idden.nickreloaded.logger.Logger;

/**
 * Register {@link me.clip.placeholderapi.PlaceholderAPI} addon.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class PlaceholderAPIAddon extends AbstractAddon
{
    PlaceholderAPIExpansion placeholderAPIExpansion = null;

    public PlaceholderAPIAddon()
    {
        this.id = "PAPI";
        this.prefix = "PlaceholderAPI Addon";
    }

    @Override
    public boolean search()
    {
        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.ADDON, "Loading " + id + " addon...");
        NickReloaded.INSTANCE.manager.logger.log(prefix, "Searching for PlaceholderAPI...");

        if (NickReloaded.INSTANCE.getServer().getPluginManager().getPlugin("PlaceholderAPI") == null)
        {
            NickReloaded.INSTANCE.manager.logger.log(prefix, "PlaceholderAPI not found. Skipping.");
            return false;
        }

        placeholderAPIExpansion = new PlaceholderAPIExpansion();
        NickReloaded.INSTANCE.manager.logger.log(prefix, "PlaceholderAPI found !");
        return true;
    }

    @Override
    public void load()
    {
        NickReloaded.INSTANCE.manager.logger.log(prefix, "Hooking into PlaceholderAPI...");

        if (placeholderAPIExpansion.register())
        {
            NickReloaded.INSTANCE.manager.logger.log(prefix, "Hooked ! You can now use NickReloaded placeholders !");
            NickReloaded.INSTANCE.manager.logger.log(Logger.Level.ADDON, "Addon " + id + " loaded.");
        }
        else
        {
            NickReloaded.INSTANCE.manager.logger.log(Logger.Level.WARNING, "Failed to hook into PlaceholderAPI, please check logs.");
        }
    }

    @Override
    public void unload()
    {
        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.ADDON, "Unhooking PlaceholderAPI...");
        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.ADDON, "Unhooked PlaceholderAPI !");
    }
}
