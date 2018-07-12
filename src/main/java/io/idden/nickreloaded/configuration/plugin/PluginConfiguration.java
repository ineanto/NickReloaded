/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.configuration.plugin;

import io.idden.nickreloaded.configuration.Configuration;

import java.util.HashMap;

/**
 * The plugin main configuration.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class PluginConfiguration extends Configuration
{
    public PluginConfiguration()
    {
        super("config");

        HashMap<String, Object> values = new HashMap<>();

        values.put("plugin.prefix", "NickReloaded");
        values.put("plugin.offline-mode", false);
        values.put("plugin.language", "fr");
        values.put("plugin.storage.sql", true);
        values.put("plugin.storage.cache", false);

        setDefaults(values);
    }
}