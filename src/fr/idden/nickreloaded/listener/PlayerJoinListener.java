package fr.idden.nickreloaded.listener;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.manager.StorageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener
        extends Listener<PlayerJoinEvent>
{
    public PlayerJoinListener()
    {
        super(NickReloaded.get());
    }

    @Override
    @EventHandler
    public void event(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        new StorageManager().load(player.getUniqueId());

        /*
        PlayerStorage playerStorage = PlayerStorage.getStorage(event.getPlayer().getUniqueId());

        if (playerStorage.isNicked())
        {
            NickReloaded.get().getNickManager().nick(player,
                                                     playerStorage.getNick(),
                                                     playerStorage.getSkin());

            Bukkit.getOnlinePlayers().forEach(oPlayer ->
                                              {
                                                  if (player.getUniqueId() != oPlayer.getUniqueId())
                                                  {
                                                      NickReloaded.get().getPayloadManager().getIdentityManager().updatePlayer(player,
                                                                                                                               ! Objects.equals(playerStorage.getSkin(),
                                                                                                                                                player.getName()));
                                                  }
                                              });
        }
        */
    }
}
