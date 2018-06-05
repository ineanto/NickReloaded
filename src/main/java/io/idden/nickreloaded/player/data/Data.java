package io.idden.nickreloaded.player.data;

import java.util.UUID;

/**
 * Manage data.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Data
{
    public UUID    uuid;
    public boolean dataLoaded = false;

    public Data(UUID uuid) { this.uuid = uuid; }

    public void loadData() //Use that in first
    {
        dataLoaded = true;
    }

    public void saveData()
    {
        dataLoaded = false;
    }

    private void loadRawData() {}

    private void saveRawData() {}

    private void saveCache() {}

    private void loadCache() {}
}
