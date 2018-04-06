package fr.antoinerochas.nickreloaded.listener;

import fr.antoinerochas.nickreloaded.NickReloaded;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class NRPlayerJoinListener
        extends NRListener<PlayerJoinEvent>
{
    public NRPlayerJoinListener()
    {
        super(NickReloaded.getInstance());
    }

    @Override
    @EventHandler
    public void event(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        //todo: nick player
    }
}
