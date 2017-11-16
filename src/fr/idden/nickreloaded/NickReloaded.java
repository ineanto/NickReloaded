package fr.idden.nickreloaded;

import fr.idden.nickreloaded.api.logger.NickReloadedLogger;
import fr.idden.nickreloaded.api.manager.NMSManager;
import fr.idden.nickreloaded.api.manager.NickManager;
import fr.idden.nickreloaded.api.placeholderapi.NRPlaceHolderExpansion;
import fr.idden.nickreloaded.api.string.Messages;
import fr.idden.nickreloaded.listener.PlayerJoinListener;
import fr.idden.nickreloaded.listener.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NickReloaded
        extends JavaPlugin
{
    private static NickReloaded instance;

    @Override
    public void onEnable()
    {
        instance = this;

        NickReloadedLogger.log(NickReloadedLogger.Level.NONE, Messages.SEPARATOR);

        NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "NickReloaded - Enabling...");

        new NMSManager().init();

        if (Bukkit.getPluginManager().isPluginEnabled("NickReloaded"))
        {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            {
                new NRPlaceHolderExpansion(this).hook();
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, Messages.PAPI_HOOK);
            }

            //LISTENERS
            new PlayerJoinListener();
            new PlayerQuitListener();

            //COMMANDS
            //• NICK
            //• NICKRELOADED (infos etc)

            NickReloadedLogger.log(NickReloadedLogger.Level.NONE, " ");
            NickReloadedLogger.log(NickReloadedLogger.Level.INFO, Messages.PLUGIN_ENABLED);
        }

        NickReloadedLogger.log(NickReloadedLogger.Level.NONE, Messages.SEPARATOR);
    }

    @Override
    public void onDisable()
    {
        new NickManager().processData(NickManager.DataStatus.DISABLING);
    }

    public static NickReloaded getInstance()
    {
        return instance;
    }
}
