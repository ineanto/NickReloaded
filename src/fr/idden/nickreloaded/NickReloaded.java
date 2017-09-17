package fr.idden.nickreloaded;

import fr.idden.nickreloaded.api.nick.NickManager;
import fr.idden.nickreloaded.api.nms.PayloadManager;
import fr.idden.nickreloaded.api.nms.throwable.PayloadModuleUnsupportedVersionException;
import fr.idden.nickreloaded.api.placeholderapi.NickReloadedPAPI;
import fr.idden.nickreloaded.api.storage.StorageManager;
import fr.idden.nickreloaded.api.storage.impl.DatabaseImpl;
import fr.idden.nickreloaded.command.AdminNickCommand;
import fr.idden.nickreloaded.command.NickCommand;
import fr.idden.nickreloaded.command.NickReloadedCommand;
import fr.idden.nickreloaded.command.UnnickCommand;
import fr.idden.nickreloaded.listener.PlayerJoinListener;
import fr.idden.nickreloaded.listener.PlayerQuitListener;
import fr.idden.nickreloaded.prefix.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NickReloaded
        extends JavaPlugin
{
    private static NickReloaded instance;
    private DatabaseImpl database;

    @Override
    public void onEnable()
    {
        instance = this;

        log(Messages.SEPARATOR.m());

        Prefix.printPrefix();

        try
        {
            new PayloadManager();
        }
        catch (PayloadModuleUnsupportedVersionException e)
        {
            disable(Messages.ERROR_PAYLOAD_UNABLE_TO_GET_ADAPTER_FOR_VERSION.m().replace("%module", e.getModule().name()));
        }

        if(Bukkit.getPluginManager().isPluginEnabled("NickReloaded"))
        {
            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            {
                new NickReloadedPAPI(this).hook();
                log(Messages.PLACEHOLDERAPI_FOUND.m());
            }

            new StorageManager().setupStorage();

            NickManager.processData(NickManager.Status.ENABLING);

            //LISTENERS
            new PlayerJoinListener();
            new PlayerQuitListener();

            //COMMANDS
            new NickCommand();
            new UnnickCommand();
            new AdminNickCommand();
            new NickReloadedCommand();

            log(" ");
            log(Messages.PLUGIN_ENABLED.m());
        }

        log(Messages.SEPARATOR.m());
    }

    @Override
    public void onDisable()
    {
        NickManager.processData(NickManager.Status.DISABLING);

        if(database != null)
        {
            database.close();
        }
    }

    public static void log(String message)
    {
        getInstance().getServer().getConsoleSender().sendMessage(Prefix.getPrefix() + message);
    }

    public static NickReloaded getInstance()
    {
        return instance;
    }

    public void setDatabase(DatabaseImpl database)
    {
        this.database = database;
    }

    public static void disable(String reason)
    {
        log(Messages.ERROR_DISABLED_PLUGIN.m());
        log(reason);
        getInstance().getPluginLoader().disablePlugin(getInstance());
    }
}
