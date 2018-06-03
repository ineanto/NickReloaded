package io.idden.nickreloaded.player;

import io.idden.nickreloaded.player.data.Data;
import io.idden.nickreloaded.player.skin.Skin;
import org.bukkit.entity.Player;

/**
 * Manage player.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class CustomPlayer
{
    public Player bukkitPlayer;

    public Data data = new Data(bukkitPlayer.getUniqueId());
    public Skin skin = new Skin(bukkitPlayer.getUniqueId());

    public CustomPlayer(Player bukkitPlayer)
    {
        this.bukkitPlayer = bukkitPlayer;
    }
}
