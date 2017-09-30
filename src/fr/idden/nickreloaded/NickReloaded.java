package fr.idden.nickreloaded;

import fr.idden.nickreloaded.api.manager.NickManager;
import fr.idden.nickreloaded.api.manager.PayloadManager;
import fr.idden.nickreloaded.api.manager.StorageManager;
import fr.idden.nickreloaded.api.nms.throwable.PayloadModuleUnsupportedVersionException;
import fr.idden.nickreloaded.api.placeholderapi.NickReloadedPAPI;
import fr.idden.nickreloaded.command.AdminNickCommand;
import fr.idden.nickreloaded.command.NickCommand;
import fr.idden.nickreloaded.command.NickReloadedCommand;
import fr.idden.nickreloaded.command.UnnickCommand;
import fr.idden.nickreloaded.listener.PlayerJoinListener;
import fr.idden.nickreloaded.listener.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NickReloaded
        extends JavaPlugin
        implements INickReloaded
{
    private static NickReloaded instance;

    @Override
    public void onEnable()
    {
        instance = this;

        log(Messages.SEPARATOR.getMessage());

        printPrefix();

        try
        {
            getPayloadManager().init();
        }
        catch (PayloadModuleUnsupportedVersionException e)
        {
            disable(Messages.ERROR_PAYLOAD_UNABLE_TO_GET_ADAPTER_FOR_VERSION.getMessage().replace("%module",
                                                                                                  e.getModule().name()));
        }

        if (Bukkit.getPluginManager().isPluginEnabled("NickReloaded"))
        {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            {
                new NickReloadedPAPI(this).hook();
                log(Messages.PLACEHOLDERAPI_FOUND.getMessage());
            }

            getStorageManager().setupStorage();

            getNickManager().processData(NickManager.DataStatus.ENABLING);

            //LISTENERS
            new PlayerJoinListener();
            new PlayerQuitListener();

            //COMMANDS
            new NickCommand();
            new UnnickCommand();
            new AdminNickCommand();
            new NickReloadedCommand();

            log(" ");
            log(Messages.PLUGIN_ENABLED.getMessage());
        }

        log(Messages.SEPARATOR.getMessage());
    }

    @Override
    public void onDisable()
    {
        getNickManager().processData(NickManager.DataStatus.DISABLING);
    }

    public static void log(String message)
    {
        get().getServer().getConsoleSender().sendMessage(get().getPrefix() + message);
    }

    public static NickReloaded get()
    {
        return instance;
    }

    public void disable(String reason)
    {
        log(Messages.ERROR_DISABLED_PLUGIN.getMessage());
        log(reason);
        get().getPluginLoader().disablePlugin(get());
    }

    public String getPrefix()
    {
        return "§f[§6NickReloaded§f] ";
    }

    public void printPrefix()
    {
        NickReloaded.log("§a _   _ _      _    ____      _                 _          _ ");
        NickReloaded.log("§2| \\ | (_) ___| | _|  _ \\ ___| | ___   __ _  __| | ___  __| |");
        NickReloaded.log("§a|  \\| | |/ __| |/ / |_) / _ \\ |/ _ \\ / _` |/ _` |/ _ \\/ _` |");
        NickReloaded.log("§2| |\\  | | (__|   <|  _ <  __/ | (_) | (_| | (_| |  __/ (_| |");
        NickReloaded.log("§a|_| \\_|_|\\___|_|\\_\\_| \\_\\___|_|\\___/ \\__,_|\\__,_|\\___|\\__,_|");
        NickReloaded.log(" ");
    }

    @Override
    public StorageManager getStorageManager()
    {
        return StorageManager.get();
    }

    @Override
    public NickManager getNickManager()
    {
        return NickManager.get();
    }

    @Override
    public PayloadManager getPayloadManager()
    {
        return PayloadManager.get();
    }
}
