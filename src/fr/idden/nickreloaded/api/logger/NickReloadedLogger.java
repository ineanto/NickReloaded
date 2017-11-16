package fr.idden.nickreloaded.api.logger;

import net.md_5.bungee.api.ProxyServer;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NickReloadedLogger
{
    public static void log(Level level, String log)
    {
        try
        {
            Bukkit.getConsoleSender().sendMessage(getDate() + level.getPrefix() + log);
        }
        catch (NoClassDefFoundError e)
        {
            ProxyServer.getInstance().getConsole().sendMessage(getDate() + level.getPrefix() + log);
        }
    }

    public static void logError(Exception e)
    {
        log(Level.WARN,
            "An error occured. (error=" + e.getMessage() + ")");
    }

    public enum Level
    {
        NONE("§6LOG"),
        INFO("§aINFO"),
        WARN("§cWARN"),
        FATAL("§4FATAL");

        private String level;

        Level(String level)
        {
            this.level = " | " + level + "§r | ";
        }

        public String getPrefix()
        {
            return level;
        }
    }

    private static String getDate()
    {
        return new SimpleDateFormat("dd/MM/YYYY kk:mm:ss").format(new Date(System.currentTimeMillis()));
    }
}
