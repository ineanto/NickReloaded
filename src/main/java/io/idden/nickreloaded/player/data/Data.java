/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.player.data;

import io.idden.nickreloaded.player.CustomPlayer;

import java.util.UUID;

/**
 * Manage data.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Data
{
    public UUID         uuid;
    public CustomPlayer customPlayer;

    public boolean dataLoaded = false;

    public Data(UUID uuid, CustomPlayer customPlayer)
    {
        this.uuid = uuid;
        this.customPlayer = customPlayer;
    }

    public void loadData() //Use that in first
    {
        dataLoaded = true;
    }

    public void saveData()
    {
        dataLoaded = false;
    }

    private void loadSQLData() {}

    private void saveSQLData() {}

    private void saveCache() {}

    private void loadCache() {}
}
