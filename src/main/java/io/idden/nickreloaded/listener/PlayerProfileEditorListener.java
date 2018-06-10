/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.listener;

import com.mojang.authlib.GameProfile;
import io.idden.nickreloaded.version.wrapper.VersionWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

/**
 * Triggered when a player joins, register a fakeprofile.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class PlayerProfileEditorListener implements Listener
{
    private Map<UUID, GameProfile> fakeProfiles;
    private VersionWrapper         versionWrapper;

    public PlayerProfileEditorListener(Map<UUID, GameProfile> fakeProfiles, VersionWrapper versionWrapper)
    {
        this.fakeProfiles = fakeProfiles;
        this.versionWrapper = versionWrapper;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        UUID uuid = event.getPlayer().getUniqueId();

        //todo: remake this with all the changes
        /*if (PlayerStorage.getStorage(uuid) != null)
        {
            if ((NickReloaded.get().getNickManager().isNicked(listener.getPlayer())) && (fakeProfiles.containsKey(uuid)))
            {
                fakeProfiles.put(uuid,
                                 versionWrapper.getFakeProfile(listener.getPlayer()));
            }
        }*/

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        UUID uuid = event.getPlayer().getUniqueId();
        fakeProfiles.remove(uuid);
    }
}