/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.listener;

import io.idden.nickreloaded.NickReloaded;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * Manage {@link Listener}s
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class ListenerManager
{
    public ArrayList<Listener> LISTENERS = new ArrayList<>();

    public ListenerManager()
    {
        LISTENERS.add(new PlayerJoinListener());
    }

    public void registerListeners()
    {
        LISTENERS.forEach(listener -> NickReloaded.INSTANCE.getServer().getPluginManager().registerEvents(listener, NickReloaded.INSTANCE));
    }

    public void unregisterListeners()
    {
        LISTENERS.clear();
    }
}
