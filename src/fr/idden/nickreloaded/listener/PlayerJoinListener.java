package fr.idden.nickreloaded.listener;

import fr.idden.nickreloaded.NickReloaded;
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

        //LOAD DATA

        /*
        PlayerStorage playerStorage = PlayerStorage.getStorage(event.getPlayer().getUniqueId());

        if (playerStorage.isNicked())
        {
            NickReloaded.getInstance().getNickManager().nick(player,
                                                     playerStorage.getNick(),
                                                     playerStorage.getSkin());

            Bukkit.getOnlinePlayers().forEach(oPlayer ->
                                              {
                                                  if (player.getUniqueId() != oPlayer.getUniqueId())
                                                  {
                                                      NickReloaded.getInstance().getPayloadManager().getIdentityManager().updatePlayer(player,
                                                                                                                               ! Objects.equals(playerStorage.getSkin(),
                                                                                                                                                player.getName()));
                                                  }
                                              });
        }
        */
    }
}
