/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.configuration;

import io.idden.nickreloaded.configuration.data.DataConfiguration;

/**
 * Manage configurations.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class ConfigurationManager
{
    DataConfiguration dataConfiguration;

    public void loadConfigurations()
    {
        dataConfiguration = new DataConfiguration();
        dataConfiguration.load();
    }

    public void saveConfigurations()
    {
        dataConfiguration.save();
    }
}
