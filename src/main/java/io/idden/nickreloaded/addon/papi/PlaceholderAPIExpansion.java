/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.addon.papi;

import io.idden.nickreloaded.NickReloaded;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

/**
 * The PlaceholderAPI {@link me.clip.placeholderapi.expansion.PlaceholderExpansion}.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class PlaceholderAPIExpansion extends PlaceholderExpansion
{
    @Override
    public String getIdentifier()
    {
        return "nickreloaded";
    }

    @Override
    public String getPlugin()
    {
        return "NickReloaded";
    }

    @Override
    public String getAuthor()
    {
        return "Antoine \"Idden\" ROCHAS";
    }

    @Override
    public String getVersion()
    {
        return NickReloaded.VERSION;
    }

    @Override
    public String onPlaceholderRequest(Player player, String s)
    {

        return "nickreloaded_" + s;
    }
}
