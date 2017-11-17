package fr.antoinerochas.nickreloaded.api.config;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.api.logger.NickReloadedLogger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFileProvider
{
    public static File config;
    public static FileConfiguration configC;
    public static ConfigurationSection configCS;

    public ConfigFileProvider(String file)
    {
        config = new File("plugins/" + NickReloaded.getInstance().getDescription().getName(),
                          file);

        if (! config.exists())
        {
            NickReloadedLogger.log(NickReloadedLogger.Level.INFO,
                                   "§aConfig doesn't exists ! Creating...");

            config.getParentFile().mkdirs();

            try
            {
                config.createNewFile();
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO,
                                       "§aConfig created !");
            }
            catch (IOException e)
            {
                NickReloadedLogger.log(NickReloadedLogger.Level.WARN,
                                       "§cOh... something went wrong while creating the config file, report this error: " + e.getMessage() + " to the plugin's page.");
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

        for (ConfigFileValues value : ConfigFileValues.values())
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
