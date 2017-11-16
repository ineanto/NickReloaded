package fr.antoinerochas.nickreloaded.api.config;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.api.logger.NickReloadedLogger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public class FileProvider
{
    public static java.io.File config;
    public static FileConfiguration configC;
    public static ConfigurationSection configCS;

    public FileProvider(String file)
    {
        config = new java.io.File("plugins/" + NickReloaded.getInstance().getDescription().getName(),
                                  file);

        if (! config.exists())
        {
            NickReloadedLogger.log(NickReloadedLogger.Level.WARN, "Config doesn't exists ! Creating...");
            config.getParentFile().mkdirs();
            try
            {
                config.createNewFile();
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "Config created !");
            }
            catch (IOException e)
            {
                NickReloadedLogger.log(NickReloadedLogger.Level.WARN, "Oh... something went wrong while creating the config file. Report this to Spigot's plugin page.");
                e.printStackTrace();
            }
        }

        load();
    }

    public void load()
    {
        configC = new YamlConfiguration();
        configCS = configC.getConfigurationSection("");

        try
        {
            configC.load(config);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }

        for (Config value : Config.values())
        {
            if (configCS.get(value.name().replaceAll("_",
                                                     ".").toLowerCase()) == null)
            {
                configCS.set(value.name().replaceAll("_",
                                                     ".").toLowerCase(),
                             value.value);
                try
                {
                    configC.save(config);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            value.value = configCS.get(value.name().replaceAll("_",
                                                               ".").toLowerCase());
        }

        try
        {
            configC.load(config);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public String getString(String path)
    {
        if (getFileConfiguration().getString(path) == null)
        {
            return "§c" + path + " (key missing)";
        }
        else
        {
            return getFileConfiguration().getString(path).replace("&",
                                                                  "§");
        }
    }

    public static FileConfiguration getFileConfiguration()
    {
        return configC;
    }
}
