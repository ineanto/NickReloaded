/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.configuration;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.logger.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Represent a plugin configurations.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Configuration
{
    public YamlConfiguration configuration;

    private File configFile;
    private HashMap<String, ?> defaults;

    public Configuration(String folder, String name)
    {
        File configFolder;

        configFolder = folder == null ? NickReloaded.INSTANCE.getDataFolder() : new File(NickReloaded.INSTANCE.getDataFolder(), folder);

        this.configFile = new File(configFolder, name + ".yml");
    }

    public Configuration(String name)
    {
        this(null, name);
    }

    public Object getEntry(String path)
    {
        return configuration.get(path);
    }

    public void setEntry(String path, Object value)
    {
        configuration.set(path, value);
        save();
    }

    public void setDefaults(HashMap<String, ?> defaults)
    {
        this.defaults = defaults;
    }

    public void setMultipleEntry(HashMap<String, Object> values)
    {
        values.forEach((path, value) -> configuration.set(path, value));
        save();
    }

    public void create()
    {
        if (! configFile.exists())
        {
            try
            {
                if(configFile.getParentFile().mkdirs() && configFile.createNewFile())
                {
                    NickReloaded.INSTANCE.manager.logger.log(Logger.Level.DEBUG, "Everything created successfully for file \"" + configFile.getName() + "\"!");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void load()
    {
        configuration = new YamlConfiguration();

        try
        {
            configuration.load(configFile);
        }
        catch (InvalidConfigurationException | IOException e)
        {
            e.printStackTrace();
        }

        if(defaults != null && !defaults.isEmpty())
        {
            defaults.forEach((key, value) ->
            {
                configuration.set(key, value);

                save();
            });
        }

        try
        {
            configuration.load(configFile);
        }
        catch (InvalidConfigurationException | IOException e)
        {
            e.printStackTrace();
        }

        NickReloaded.INSTANCE.manager.logger.log("Config", "Loaded \"" + configFile.getName() + "\"!");
    }

    public void save()
    {
        if(configuration == null)
        {
            load();
        }

        try
        {
            configuration.save(configFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
