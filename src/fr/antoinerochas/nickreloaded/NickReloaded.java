package fr.antoinerochas.nickreloaded;

import fr.antoinerochas.nickreloaded.api.logger.NickReloadedLogger;
import fr.antoinerochas.nickreloaded.api.manager.NMSManager;
import fr.antoinerochas.nickreloaded.api.manager.NickManager;
import fr.antoinerochas.nickreloaded.api.manager.StorageManager;
import fr.antoinerochas.nickreloaded.api.placeholderapi.PlaceHolderAPIHook;
import fr.antoinerochas.nickreloaded.api.string.Messages;
import fr.antoinerochas.nickreloaded.api.updater.NickReloadedUpdater;
import fr.antoinerochas.nickreloaded.listener.PlayerJoinListener;
import fr.antoinerochas.nickreloaded.listener.PlayerQuitListener;
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

        NickReloadedLogger.log(NickReloadedLogger.Level.NONE,
                               Messages.SEPARATOR);

        NickReloadedLogger.log(NickReloadedLogger.Level.INFO,
                               "§aNickReloaded - Enabling...");

        init();

        NickReloadedLogger.log(NickReloadedLogger.Level.NONE,
                               Messages.SEPARATOR);
    }

    private void init()
    {
        Bukkit.getScheduler().runTaskLaterAsynchronously(NickReloaded.getInstance(),
                                                         this::callUpdater,
                                                         20L);

        NMSManager.getInstance().init();

        StorageManager.getInstance().setupStorage();

        if (Bukkit.getPluginManager().isPluginEnabled("NickReloaded"))
        {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            {
                new PlaceHolderAPIHook(this).hook();
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO,
                                       Messages.PAPI_HOOK);
            }

            //LISTENERS
            new PlayerJoinListener();
            new PlayerQuitListener();

            //COMMANDS
            //• NICK
            //• NICKRELOADED (infos etc)

            NickReloadedLogger.log(NickReloadedLogger.Level.NONE,
                                   " ");
            NickReloadedLogger.log(NickReloadedLogger.Level.NONE,
                                   Messages.PLUGIN_ENABLED);
        }
    }

    @Override
    public void onDisable()
    {
        new NickManager().processData(NickManager.DataStatus.DISABLING);
    }

    private void callUpdater()
    {
        NickReloadedLogger.log(NickReloadedLogger.Level.WARN,
                               "§aChecking for updates...");

        final NickReloadedUpdater updater = new NickReloadedUpdater(this,
                                                                    46335,
                                                                    false);

        final NickReloadedUpdater.UpdateResult result = updater.getResult();

        switch (result)
        {
            case FAIL_SPIGOT:
            {
                NickReloadedLogger.log(NickReloadedLogger.Level.FATAL,
                                       "§4The updater could not contact spigot.");
                break;
            }

            case NO_UPDATE:
            {
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO,
                                       "§aNickReloaded is up to date !");
                break;
            }

            case UPDATE_AVAILABLE:
            {
                String new_version = updater.getVersion();
                NickReloadedLogger.log(NickReloadedLogger.Level.WARN, "§cAn update is available ! (" + new_version + ")");
                NickReloadedLogger.log(NickReloadedLogger.Level.WARN, "§cDownload at: https://www.spigotmc.org/resources/nickreloaded.46335/ !");
                break;
            }

            default:
            {
                NickReloadedLogger.log(NickReloadedLogger.Level.WARN,
                                       result.toString());
                break;
            }
        }
    }

    public static NickReloaded getInstance()
    {
        return instance;
    }
}
