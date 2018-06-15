/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.logger;

import org.bukkit.Bukkit;

/**
 * The logger, revisited.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Logger
{
    public void log(Level level, String log)
    {
        log(level.prefix, log);
    }

    public void log(String prefix, String log)
    {
        if (prefix == null)
        {
            prefix = Level.NONE.prefix;
        }

        Bukkit.getConsoleSender().sendMessage("[" + prefix + "/Log]: " + log);
    }

    public enum Level
    {
        NONE(""),
        PLUGIN("Plugin"),
        ADDON("Addon"),
        WARNING("WARNING"),
        FATAL("FATAL");

        public String prefix;

        Level(String prefix)
        {
            this.prefix = prefix;
        }
    }
}
