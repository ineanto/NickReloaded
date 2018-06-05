package io.idden.nickreloaded.addon;

import io.idden.nickreloaded.addon.papi.PlaceholderAPIAddon;

import java.util.HashMap;

/**
 * Manage addon.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class AddonManager
{
    private HashMap<String, AbstractAddon> ADDONS = new HashMap<>();

    public void loadAddons()
    {
        PlaceholderAPIAddon placeholderAPIAddon = new PlaceholderAPIAddon();

        //Registering into map
        ADDONS.putIfAbsent(placeholderAPIAddon.id, placeholderAPIAddon);
    }

    public void unloadAddons()
    {
        ADDONS.forEach((id, addon) ->
        {
            addon.unregister();
            ADDONS.remove(id);
        } );
    }
}
