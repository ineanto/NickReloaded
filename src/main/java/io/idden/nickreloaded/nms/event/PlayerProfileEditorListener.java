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

package io.idden.nickreloaded.nms.event;

import com.mojang.authlib.GameProfile;
import io.idden.nickreloaded.version.wrapper.VersionWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

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
            if ((NickReloaded.get().getNickManager().isNicked(event.getPlayer())) && (fakeProfiles.containsKey(uuid)))
            {
                fakeProfiles.put(uuid,
                                 versionWrapper.getFakeProfile(event.getPlayer()));
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