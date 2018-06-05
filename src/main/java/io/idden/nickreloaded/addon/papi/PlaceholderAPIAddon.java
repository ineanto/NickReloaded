package io.idden.nickreloaded.addon.papi;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.addon.AbstractAddon;

/**
 * Register {@link me.clip.placeholderapi.PlaceholderAPI} addon.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class PlaceholderAPIAddon extends AbstractAddon
{
    public PlaceholderAPIAddon()
    {
        this.id = "PlaceholderAPI-Addon";
    }

    @Override
    public void register()
    {
        if(NickReloaded.INSTANCE.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null)
        {

        }
    }

    @Override
    public void unregister()
    {

    }
}
