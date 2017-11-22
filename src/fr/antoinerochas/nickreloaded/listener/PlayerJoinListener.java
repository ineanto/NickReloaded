package fr.antoinerochas.nickreloaded.listener;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.api.manager.NickManager;
import fr.antoinerochas.nickreloaded.api.storage.AccountProvider;
import fr.antoinerochas.nickreloaded.api.storage.NickData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener
        extends Listener<PlayerJoinEvent>
{
    public PlayerJoinListener()
    {
        super(NickReloaded.getInstance());
    }

    @Override
    @EventHandler
    public void event(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        AccountProvider accountProvider = new AccountProvider(player.getUniqueId());
        NickData nickData = accountProvider.getData();

        if (nickData.isNicked())
        {
            NickManager.getManager(player).nick(nickData.getName(),
                                                nickData.getSkin());
        }
    }
}
