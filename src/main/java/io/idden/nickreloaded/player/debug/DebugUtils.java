/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.player.debug;

import org.bukkit.entity.Player;

/**
 * Manage player debug.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class DebugUtils
{
    public Player player;

    public DebugUtils(Player player)
    {
        this.player = player;
    }

    public void sendDebug(String debug)
    {
        player.sendMessage("§d§l[DEBUG]: §f" + debug);
    }
}
