package fr.idden.nickreloaded.listener;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.manager.StorageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends Listener<PlayerQuitEvent>
{
    public PlayerQuitListener()
    {
        super(NickReloaded.getInstance());
    }

    @Override
    @EventHandler
    public void event(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();

        NickReloaded.getInstance().getNickManager().stopTask(player);
        new StorageManager().save(player.getUniqueId());
    }
}
