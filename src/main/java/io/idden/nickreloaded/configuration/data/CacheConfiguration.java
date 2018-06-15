/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.configuration.data;

import io.idden.nickreloaded.configuration.Configuration;

import java.util.HashMap;

/**
 * The data configuration.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class CacheConfiguration extends Configuration
{
    public CacheConfiguration()
    {
        super("data", "cache");

        HashMap<String, Object> values = new HashMap<>();

        values.put("cache.ip", "127.0.0.1");
        values.put("cache.port", 3306);
        values.put("cache.user", "root");
        values.put("cache.password", "p@ssw0rD");
        values.put("cache.database", 0);

        setDefaults(values);
    }
}
