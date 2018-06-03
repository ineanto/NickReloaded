package io.idden.nickreloaded.core.nms.event;

import com.mojang.authlib.GameProfile;
import io.idden.nickreloaded.core.nms.impl.PlayerIdentityManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerProfileEditorListener
        implements Listener
{
    private static Map<UUID, GameProfile> fakeProfiles = new HashMap<>();
    private static PlayerIdentityManager  playerIdentityManager;

    public PlayerProfileEditorListener(Map<UUID, GameProfile> fakeProfiles, PlayerIdentityManager playerIdentityManager)
    {
        this.fakeProfiles = fakeProfiles;
        this.playerIdentityManager = playerIdentityManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        UUID uuid = event.getPlayer().getUniqueId();
        /*if (PlayerStorage.getStorage(uuid) != null)
        {
            if ((NickReloaded.get().getNickManager().isNicked(event.getPlayer())) && (fakeProfiles.containsKey(uuid)))
            {
                fakeProfiles.put(uuid,
                                 playerIdentityManager.getFakeProfile(event.getPlayer()));
            }
        }*/

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        UUID uuid = event.getPlayer().getUniqueId();
        if (fakeProfiles.containsKey(uuid))
        {
            fakeProfiles.remove(uuid);
        }
    }
}