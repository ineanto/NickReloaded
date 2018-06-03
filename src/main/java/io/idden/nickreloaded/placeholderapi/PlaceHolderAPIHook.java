package io.idden.nickreloaded.placeholderapi;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlaceHolderAPIHook
        extends EZPlaceholderHook
{

    public PlaceHolderAPIHook(Plugin plugin)
    {
        super(plugin, "nickreloaded");
    }

    @Override
    public String onPlaceholderRequest(Player player, String placeholder)
    {
        if(player != null)
        {
            /*PlayerStorage playerStorage = PlayerStorage.getStorage(player.getUniqueId());

            if(playerStorage.isNicked())
            {
                switch (placeholder)
                {
                    case "manager":
                        return playerStorage.getNick();
                    case "skin":
                        return playerStorage.getSkin();

                    default:
                        return "nickreloaded: no placeholder for " + placeholder;
                }
            }*/

        }
        return null;
    }
}
