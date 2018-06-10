/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.listener;

import io.idden.nickreloaded.player.CustomPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Triggered when a player joins.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player       player       = event.getPlayer();
        CustomPlayer customPlayer = new CustomPlayer(player);

        if(customPlayer.apparence.disguised)
        {
        }

        customPlayer.data.loadData();
    }
}
