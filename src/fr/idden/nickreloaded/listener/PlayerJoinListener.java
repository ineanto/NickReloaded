package fr.idden.nickreloaded.listener;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.nick.NickManager;
import fr.idden.nickreloaded.api.nms.PayloadManager;
import fr.idden.nickreloaded.api.storage.PlayerStorage;
import fr.idden.nickreloaded.api.storage.StorageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener
        extends Listener<PlayerJoinEvent>
{
    public PlayerJoinListener()
    {
        super(NickReloaded.getInstance());
    }

    @Override
    @EventHandler(priority = EventPriority.HIGH)
    public void event(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        new StorageManager().load(player.getUniqueId());

        PlayerStorage playerStorage = PlayerStorage.getStorage(event.getPlayer().getUniqueId());

        if (playerStorage.isNicked())
        {
            NickManager.nick(player,
                             playerStorage.getNick(),
                             playerStorage.getSkin());
        }

        Bukkit.getOnlinePlayers().forEach(oPlayer -> {
            if (player.getUniqueId() != oPlayer.getUniqueId())
            {
                PayloadManager.getIdentityManager().updatePlayer(player,
                                                                 true);
            }
        });
    }
}
