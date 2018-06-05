/*
 * MIT License
 *
 * Copyright (c) 2017 Antoine "Idden" ROCHAS
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

package io.idden.nickreloaded.version.wrapper;

import com.mojang.authlib.GameProfile;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Reprensent a Wrapper for different Minecraft NMS versions.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public interface VersionWrapper
{
    void sendActionbar(Player player, String message);

    GameProfile fillGameprofile(GameProfile gameProfile);

    void updatePlayerProfile(Object packet);

    void setPlayerName(Player player, String name);

    String getPlayerName(Player player);

    void setPlayerSkin(Player player, String skin);

    void updatePlayer(Player player, final boolean isSkinChanging);

    GameProfile getFakeProfile(Player player);

    GameProfile getPlayerProfile(Player player);

    void setProfileName(GameProfile gameProfile, String name);

    void setProfileId(GameProfile gameProfile, UUID uuid);
}