package fr.antoinerochas.nickreloaded.core.logger;

import net.md_5.bungee.api.ProxyServer;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class used to log all the NickReloaded's related content.
 *
 * @author Antoine "Idden" R.
 * @since 2.0-rc1
 */
public class NRLogger
{
    public void log(NRLPrefix nrlprefix, String log)
    {
        try
        {
            Bukkit.getConsoleSender().sendMessage(getDate() + nrlprefix.getPrefix() + log);
        }
        catch (NoClassDefFoundError e)
        {
            ProxyServer.getInstance().getConsole().sendMessage(getDate() + nrlprefix.getPrefix() + log);
        }
    }

    public void logBlank()
    {
        log(NRLPrefix.NONE, " ");
    }

    private static String getDate()
    {
        return new SimpleDateFormat("dd/MM/YYYY kk:mm:ss").format(new Date(System.currentTimeMillis()));
    }
}
