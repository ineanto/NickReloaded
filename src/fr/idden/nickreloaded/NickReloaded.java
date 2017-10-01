package fr.idden.nickreloaded;

import fr.idden.nickreloaded.api.manager.NickManager;
import fr.idden.nickreloaded.api.manager.PayloadManager;
import fr.idden.nickreloaded.api.manager.StorageManager;
import fr.idden.nickreloaded.api.nms.throwable.PayloadUnsupportedVersionException;
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
        catch (PayloadUnsupportedVersionException e)
        {
            e.printStackTrace();
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

    public void disable(String reason)
    {
        log(Messages.ERROR_DISABLED_PLUGIN.getMessage());
        log(reason);
        getInstance().getPluginLoader().disablePlugin(getInstance());
    }

    public void printPrefix()
    {
        log("§a _   _ _      _    ____      _                 _          _ ");
        log("§2| \\ | (_) ___| | _|  _ \\ ___| | ___   __ _  __| | ___  __| |");
        log("§a|  \\| | |/ __| |/ / |_) / _ \\ |/ _ \\ / _` |/ _` |/ _ \\/ _` |");
        log("§2| |\\  | | (__|   <|  _ <  __/ | (_) | (_| | (_| |  __/ (_| |");
        log("§a|_| \\_|_|\\___|_|\\_\\_| \\_\\___|_|\\___/ \\__,_|\\__,_|\\___|\\__,_|");
        log(" ");
    }

    public void log(String message)
    {
        getServer().getConsoleSender().sendMessage(getInstance().getPrefix() + message);
    }

    public String getPrefix()
    {
        return "§f[§6NickReloaded§f] ";
    }

    public static NickReloaded getInstance()
    {
        return instance;
    }

    public StorageManager getStorageManager()
    {
        return new StorageManager();
    }

    public NickManager getNickManager()
    {
        return new NickManager();
    }

    public PayloadManager getPayloadManager()
    {
        return new PayloadManager();
    }
}
