/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.addon;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.addon.papi.PlaceholderAPIAddon;
import io.idden.nickreloaded.logger.Logger;

import java.util.HashMap;

/**
 * Manage addons.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class AddonManager
{
    private HashMap<String, AbstractAddon> ADDONS = new HashMap<>();

    public void loadAddons()
    {
        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.ADDON, "Loading addons...");

        PlaceholderAPIAddon placeholderAPIAddon = new PlaceholderAPIAddon();

        if (placeholderAPIAddon.search())
        {
            placeholderAPIAddon.load();
            ADDONS.put(placeholderAPIAddon.id, placeholderAPIAddon);
        }

        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.ADDON, "Addons loaded !");
    }

    public void unloadAddons()
    {
        ADDONS.forEach((id, addon) ->
        {
            addon.unload();
            ADDONS.remove(id);
        });
    }
}
