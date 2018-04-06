package fr.antoinerochas.nickreloaded.core.config;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.core.logger.NRLogger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LanguageFileProvider
{
    public static java.io.File config;
    public static FileConfiguration configC;
    public static ConfigurationSection configCS;

    public LanguageFileProvider(String file)
    {
        config = new File("plugins/" + NickReloaded.getInstance().getDescription().getName(),
                          file);

        if (! config.exists())
        {
            NRLogger.log(NRLogger.NRLPrefix.INFO,
                         "§aLanguage file doesn't exists ! Creating...");

            config.getParentFile().mkdirs();

            try
            {
                config.createNewFile();
                NRLogger.log(NRLogger.NRLPrefix.INFO,
                             "§aLanguage file created !");
            }
            catch (IOException e)
            {
                NRLogger.log(NRLogger.NRLPrefix.WARN,
                                       "§cOh... something went wrong while creating the language file, report this error: " + e.getMessage() + " to the plugin's page.");
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

        for (LanguageFileValues value : LanguageFileValues.values())
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
