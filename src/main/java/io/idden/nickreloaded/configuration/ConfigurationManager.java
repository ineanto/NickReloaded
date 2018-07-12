/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.configuration;

import io.idden.nickreloaded.configuration.data.CacheConfiguration;
import io.idden.nickreloaded.configuration.data.SQLConfiguration;
import io.idden.nickreloaded.configuration.plugin.PluginConfiguration;

import java.util.ArrayList;

/**
 * Manage configurations.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class ConfigurationManager
{
    public PluginConfiguration pluginConfiguration;
    public SQLConfiguration sqlConfiguration;
    public CacheConfiguration cacheConfiguration;

    private ArrayList<Configuration> configurations = new ArrayList<>();

    public void loadConfigurations()
    {
        configurations.add(pluginConfiguration = new PluginConfiguration());
        configurations.add(sqlConfiguration = new SQLConfiguration());
        configurations.add(cacheConfiguration = new CacheConfiguration());

        configurations.forEach(configuration ->
        {
            configuration.create();
            configuration.load();
        });
    }

    public void saveConfigurations()
    {
        configurations.forEach(Configuration::save);
        configurations.clear();
    }
}
