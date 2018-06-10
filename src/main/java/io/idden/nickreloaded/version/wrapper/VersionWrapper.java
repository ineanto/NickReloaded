/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
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