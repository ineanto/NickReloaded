package fr.idden.nickreloaded.api.nms.event;

import com.mojang.authlib.GameProfile;
import fr.idden.nickreloaded.api.nick.NickManager;
import fr.idden.nickreloaded.api.nms.impl.AbstractPlayerIdentityManager;
import fr.idden.nickreloaded.api.storage.PlayerStorage;
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
    private static AbstractPlayerIdentityManager abstractPlayerIdentityManager;

    public PlayerProfileEditorListener(Map<UUID, GameProfile> fakeProfiles, AbstractPlayerIdentityManager abstractPlayerIdentityManager)
    {
        this.fakeProfiles = fakeProfiles;
        this.abstractPlayerIdentityManager = abstractPlayerIdentityManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        UUID uuid = event.getPlayer().getUniqueId();
        if (PlayerStorage.getStorage(uuid) != null)
        {
            if ((NickManager.isNicked(event.getPlayer())) && (fakeProfiles.containsKey(uuid)))
            {
                fakeProfiles.put(uuid,
                                 abstractPlayerIdentityManager.getFakeProfile(event.getPlayer()));
            }
        }

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