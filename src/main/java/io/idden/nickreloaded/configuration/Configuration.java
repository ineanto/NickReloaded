/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.configuration;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.logger.Logger;
import org.apache.commons.lang3.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Represent a plugin configuration.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Configuration
{
    public String            fileName;
    public YamlConfiguration configuration;
    public boolean           loaded;

    private File file;

    public Configuration(String fileName)
    {
        this.fileName = fileName;
        this.file = new File(NickReloaded.INSTANCE.getDataFolder(), fileName);

        if (setupConfiguration())
        {
            NickReloaded.INSTANCE.manager.logger.log("Config", "Created configuration file: \"" + fileName + "\".");
        }
    }

    private boolean setupConfiguration()
    {
        if (! file.exists())
        {
            file.getParentFile().mkdirs();
            NickReloaded.INSTANCE.saveResource(fileName + ".yml", false);
            configuration = new YamlConfiguration();
        }

        return false;
    }

    public Object getEntry(String path)
    {
        Validate.isTrue(loaded, "Configuration isn't loaded");
        return configuration.get(path);
    }

    public void setEntry(String path, Object value)
    {
        Validate.isTrue(loaded, "Configuration isn't loaded");
        configuration.set(path, value);
    }

    public void save()
    {
        Validate.notNull(configuration, "Configuration is null");

        try
        {
            configuration.save(file);
            loaded = false;
        }
        catch (IOException e)
        {
            NickReloaded.INSTANCE.manager.logger.log(Logger.Level.WARNING, "Failed to save configuration: \"" + fileName + "\":");
            e.printStackTrace();
        }
    }

    public void load()
    {
        Validate.notNull(file, "File is null");

        try
        {
            configuration.load(file);
            loaded = true;
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }
}
