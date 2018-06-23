/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.configuration;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.logger.Logger;
import org.apache.commons.lang3.Validate;
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

    public Configuration(String folder, String name)
    {
        File configFolder;
        if (folder == null) { configFolder = NickReloaded.INSTANCE.getDataFolder(); }
        else { configFolder = new File(NickReloaded.INSTANCE.getDataFolder(), folder); }

        this.configFile = new File(configFolder, name + ".yml");

        create();
        load();
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

    public void setMultipleEntry(HashMap<String, Object> values)
    {
        values.forEach((path, value) -> configuration.set(path, value));
        save();
    }

    public void setDefaults(HashMap<String, Object> values)
    {
        values.forEach((path, value) ->
        {
            if(getEntry(path) == null)
            {
                configuration.set(path, value);
            }
        });

        save();
    }

    public void load()
    {
        configuration = YamlConfiguration.loadConfiguration(configFile);
        NickReloaded.INSTANCE.manager.logger.log("Config", "Loaded \"" + configFile.getName() + "\"!");
    }

    private void create()
    {
        if (! configFile.exists())
        {
            if (! configFile.getParentFile().exists())
            {
                configFile.getParentFile().mkdirs();
            }

            try
            {
                configFile.createNewFile();
                NickReloaded.INSTANCE.manager.logger.log(Logger.Level.DEBUG, "Everything created successfully for file \"" + configFile.getName() + "\"!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void save()
    {
        Validate.notNull(configuration);

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
