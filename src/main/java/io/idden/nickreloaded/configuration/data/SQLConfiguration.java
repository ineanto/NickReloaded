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
public class SQLConfiguration extends Configuration
{
    public SQLConfiguration()
    {
        super("data", "sql");

        HashMap<String, Object> values = new HashMap<>();

        values.put("sql.ip", "127.0.0.1");
        values.put("sql.port", 3306);
        values.put("sql.user", "root");
        values.put("sql.password", "p@ssw0rD");
        values.put("sql.database", "nickreloaded");

        setDefaults(values);
    }
}
