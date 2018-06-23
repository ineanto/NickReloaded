/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.player;

import io.idden.nickreloaded.player.data.Data;
import io.idden.nickreloaded.player.debug.DebugUtils;
import io.idden.nickreloaded.player.skin.Apparence;
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

    public final Data       data;
    public final Apparence  apparence;
    public final DebugUtils debugUtils;

    public CustomPlayer(Player bukkitPlayer)
    {
        this.bukkitPlayer = bukkitPlayer;
        this.data = new Data(bukkitPlayer.getUniqueId(), this);
        this.apparence = data.loadData();
        this.debugUtils = new DebugUtils(bukkitPlayer);
    }
}
