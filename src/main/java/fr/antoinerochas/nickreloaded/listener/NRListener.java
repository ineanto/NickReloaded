package fr.antoinerochas.nickreloaded.listener;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class NRListener<E extends Event> implements org.bukkit.event.Listener
{
    public NRListener(JavaPlugin javaPlugin)
    {
        javaPlugin.getServer().getPluginManager().registerEvents(this, javaPlugin);
    }

    public abstract void event(E event);
}
