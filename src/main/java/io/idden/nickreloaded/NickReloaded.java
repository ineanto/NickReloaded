package io.idden.nickreloaded;

import io.idden.nickreloaded.init.Manager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of NickReloaded.
 *
 * @author Antoine "Idden" R.
 * @since 1.0-rc1
 */
public class NickReloaded extends JavaPlugin
{
    public static NickReloaded INSTANCE = new NickReloaded();

    Manager manager = new Manager();

    @Override
    public void onEnable()
    {
        manager.start();
    }

    @Override
    public void onLoad()
    {
        manager.onLoad();
    }

    @Override
    public void onDisable()
    {
        manager.stop();
    }
}
