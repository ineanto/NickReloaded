package fr.idden.nickreloaded.api.config;

import fr.idden.nickreloaded.NickReloaded;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigFile
{
    public static File config;
    public static FileConfiguration configC;
    public static ConfigurationSection configCS;

    public ConfigFile(JavaPlugin javaPlugin, String file)
    {
        config = new File("plugins/" + javaPlugin.getDescription().getName(),
                          file);

        if (! config.exists())
        {
            NickReloaded.log("Config doesn't exists ! Creating...");
            config.getParentFile().mkdirs();
            try
            {
                config.createNewFile();
                NickReloaded.log("Config created !");
            }
            catch (IOException e)
            {
                NickReloaded.log("Oh... something went wrong while creating the config file... Report this to Spigot's plugin page.");
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

    public String getString(String path, boolean dynamic)
    {
        if(getConfigC().getString(path) == null)
        {
            return "§c" + path + " (key missing)";
        }
        else
        {
            if(dynamic)
            {
                return getConfigC().getString(path).replace("&", "§");
            }
            else
            {
                return getConfigC().getString("prefix").replace("&", "§") + getConfigC().getString(path).replace("&", "§");
            }
        }
    }


    public static File getConfig()
    {
        return config;
    }

    public static FileConfiguration getConfigC()
    {
        return configC;
    }

    public static ConfigurationSection getConfigCS()
    {
        return configCS;
    }
}
