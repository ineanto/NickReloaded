package fr.idden.nickreloaded.api.placeholderapi;

import fr.idden.nickreloaded.api.storage.PlayerStorage;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class NickReloadedPAPI extends EZPlaceholderHook
{

    public NickReloadedPAPI(Plugin plugin)
    {
        super(plugin, "nickreloaded");
    }

    @Override
    public String onPlaceholderRequest(Player player, String placeholder)
    {
        if(player != null)
        {
            PlayerStorage playerStorage = PlayerStorage.getStorage(player.getUniqueId());

            if(playerStorage.isNicked())
            {
                switch (placeholder)
                {
                    case "nick":
                        return playerStorage.getNick();
                    case "skin":
                        return playerStorage.getSkin();

                    default:
                        return "nickreloaded: no placeholder for " + placeholder;
                }
            }

        }
        return null;
    }
}
