package fr.idden.nickreloaded.api.storage;

public enum StorageMode
{
    MYSQL,
    SQLITE;

    private static StorageMode storageMode;

    public static StorageMode getMode()
    {
        return storageMode;
    }


    public static boolean isMode(StorageMode mode)
    {
        return storageMode == mode;
    }

    public static void setMode(StorageMode mode)
    {
        storageMode = mode;
    }
}
