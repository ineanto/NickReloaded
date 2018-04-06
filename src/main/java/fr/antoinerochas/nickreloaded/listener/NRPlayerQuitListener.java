package fr.antoinerochas.nickreloaded.listener;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.core.manager.NRNickManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class NRPlayerQuitListener extends NRListener<PlayerQuitEvent>
{
    public NRPlayerQuitListener()
    {
        super(NickReloaded.getInstance());
    }

    @Override
    @EventHandler
    public void event(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();

        NRNickManager.getManager(player).unnick();
    }
}
