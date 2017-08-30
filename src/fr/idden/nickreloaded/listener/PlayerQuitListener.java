package fr.idden.nickreloaded.listener;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.nick.NickManager;
import fr.idden.nickreloaded.api.storage.StorageManager;
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

        NickManager.stopTask(player);
        new StorageManager().save(player.getUniqueId());
    }
}
