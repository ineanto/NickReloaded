/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.player.skin;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.NickReloadedConstants;
import io.idden.nickreloaded.player.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Manage apparence.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Apparence implements Serializable
{
    public ArrayList<UUID> nicked = new ArrayList<>();

    public UUID   uuid;
    public String name;

    public transient Player       player;
    public transient CustomPlayer customPlayer;

    public boolean disguised = false;
    public String  dSkin     = null;
    public String  dName     = null;

    public Apparence(UUID uuid, CustomPlayer customPlayer)
    {
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);
        this.name = player.getName();
        this.customPlayer = customPlayer;
    }

    public void setApparence(String name, String skin)
    {
        if (name == null && skin == null)
        {
            throw new UnsupportedOperationException("Can't apply null data to player");
        }

        if (! disguised)
        {
            Bukkit.getScheduler().runTaskAsynchronously(NickReloaded.INSTANCE, () ->
            {
                if (name != null)
                {
                    setName(name);
                }

                if (skin != null)
                {
                    setSkin(skin);
                }

                nicked.add(uuid);
                disguised = true;
            });
        }
    }

    private void setName(String name)
    {
        NickReloadedConstants.WRAPPER.setPlayerName(player, name);
        dName = name;
    }

    private void setSkin(String skin)
    {
        NickReloadedConstants.WRAPPER.setPlayerSkin(player, skin);
        dSkin = skin;
    }

    public void resetName()
    {
        NickReloadedConstants.WRAPPER.setPlayerName(player, name);
        dName = null;
    }

    public void resetSkin()
    {
        NickReloadedConstants.WRAPPER.setPlayerSkin(player, name);
        dSkin = null;
    }

    public void reset()
    {
        Bukkit.getScheduler().runTaskAsynchronously(NickReloaded.INSTANCE, () ->
        {
            resetName();
            resetSkin();
            nicked.remove(uuid);
            disguised = false;
        });
    }
}
