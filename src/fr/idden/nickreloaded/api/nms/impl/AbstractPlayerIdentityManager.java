package fr.idden.nickreloaded.api.nms.impl;

import com.mojang.authlib.GameProfile;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface AbstractPlayerIdentityManager
{
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
