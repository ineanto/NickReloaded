package fr.antoinerochas.nickreloaded.api.storage.core;

public enum CacheStorageMode
{
    REDIS,
    PLUGIN_MESSAGE;

    private static CacheStorageMode databaseStorageMode;

    public static CacheStorageMode getMode()
    {
        return databaseStorageMode;
    }


    public static boolean isMode(CacheStorageMode mode)
    {
        return databaseStorageMode == mode;
    }

    public static void setMode(CacheStorageMode mode)
    {
        databaseStorageMode = mode;
    }
}
