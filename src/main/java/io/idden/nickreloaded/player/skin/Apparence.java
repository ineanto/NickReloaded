/*
 *  MIT License
 *
 *  Copyright (c) 2017-2018 Antoine "Idden" ROCHAS
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.idden.nickreloaded.player.skin;

import io.idden.nickreloaded.core.Manager;
import io.idden.nickreloaded.player.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.UUID;

/**
 * Manage apparence.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Apparence implements Serializable
{
    public UUID   uuid;
    public String name;

    public transient Player       player;
    public transient CustomPlayer customPlayer;

    public boolean disguised;
    public String  dSkin = null;
    public String  dName = null;

    public Apparence(UUID uuid, CustomPlayer customPlayer)
    {
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);
        this.name = player.getName();
        this.customPlayer = customPlayer;
    }

    public void setName(String name)
    {
        Manager.WRAPPER.setPlayerName(player, name);
        dName = name;
    }

    public void setSkin(String skin)
    {
        Manager.WRAPPER.setPlayerSkin(player, skin);
        dSkin = skin;
    }

    public void resetName()
    {
        Manager.WRAPPER.setPlayerName(player, name);
        dName = null;
    }

    public void resetSkin()
    {
        Manager.WRAPPER.setPlayerSkin(player, name);
        dSkin = null;
    }

    public void reset()
    {
        resetName();
        resetSkin();
    }
}
