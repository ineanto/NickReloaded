package fr.antoinerochas.nickreloaded.api.storage.core;

public enum DatabaseStorageMode
{
    MYSQL,
    SQLITE;

    private static DatabaseStorageMode databaseStorageMode;

    public static DatabaseStorageMode getMode()
    {
        return databaseStorageMode;
    }


    public static boolean isMode(DatabaseStorageMode mode)
    {
        return databaseStorageMode == mode;
    }

    public static void setMode(DatabaseStorageMode mode)
    {
        databaseStorageMode = mode;
    }
}
