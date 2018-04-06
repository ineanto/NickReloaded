package fr.antoinerochas.nickreloaded;

import fr.antoinerochas.nickreloaded.core.logger.NRLPrefix;
import fr.antoinerochas.nickreloaded.core.manager.NRPluginManager;
import fr.antoinerochas.nickreloaded.core.manager.NRWrapperLoader;
import fr.antoinerochas.nickreloaded.core.manager.NRNickManager;
import fr.antoinerochas.nickreloaded.core.manager.StorageManager;
import fr.antoinerochas.nickreloaded.core.placeholderapi.PlaceHolderAPIHook;
import fr.antoinerochas.nickreloaded.core.string.NRMessages;
import fr.antoinerochas.nickreloaded.listener.NRPlayerJoinListener;
import fr.antoinerochas.nickreloaded.listener.NRPlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of NickReloaded.
 *
 * @author Antoine "Idden" R.
 * @since 1.0-rc1
 */
public class NickReloaded
        extends JavaPlugin
{
    private NickReloaded instance;

    private NRPluginManager plmanager;

    @Override
    public void onEnable()
    {
        instance = this;
        plmanager = new NRPluginManager();

        plmanager.getLogger().log(NRLPrefix.NONE,
                   NRMessages.SEPARATOR);

        plmanager.getLogger().log(NRLPrefix.INFO,
                   "§aNickReloaded - Enabling...");

        init();

        plmanager.getLogger().log(NRLPrefix.NONE,
                   NRMessages.SEPARATOR);
    }

    private void init()
    {
        //todo: updater ?

        NRWrapperLoader.get().init();

        NRNickManager.getManager().processData(NRNickManager.ProcessMethod.ENABLING);

        StorageManager.getInstance().setupStorage();

        if (Bukkit.getPluginManager().isPluginEnabled("NickReloaded"))
        {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            {
                new PlaceHolderAPIHook(this).hook();
                plmanager.getLogger().log(NRLPrefix.INFO,
                           NRMessages.PAPI_HOOK);
            }

            //LISTENERS
            new NRPlayerJoinListener();
            new NRPlayerQuitListener();

            //COMMANDS
            //• NICK
            //• NICKRELOADED (infos etc)

            plmanager.getLogger().logBlank();
            plmanager.getLogger().log(NRLPrefix.NONE,
                       NRMessages.PLUGIN_ENABLED);
        }
    }

    @Override
    public void onDisable()
    {
        plmanager.getLogger().log(NRLPrefix.INFO, "NickReloaded is disabling...");
        NRNickManager.getManager().processData(NRNickManager.ProcessMethod.DISABLING);
        plmanager.getLogger().log(NRLPrefix.INFO, "Disable complete, good bye :) !");
    }

    public static NRPluginManager getPluginManager()
    {
        return getInstance().plmanager;
    }

    public static NickReloaded getInstance()
    {
        return new NickReloaded();
    }
}
