package fr.idden.nickreloaded.api.manager;

import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.FileProvider;
import fr.idden.nickreloaded.api.logger.NickReloadedLogger;
import fr.idden.nickreloaded.api.storage.core.DatabaseImpl;
import fr.idden.nickreloaded.api.storage.core.RequestHandler;
import fr.idden.nickreloaded.api.storage.core.StorageMode;
import fr.idden.nickreloaded.api.storage.mysql.MySQLDatabase;
import fr.idden.nickreloaded.api.storage.mysql.Table;
import fr.idden.nickreloaded.api.storage.sqlite.SQLiteDatabase;

public class StorageManager
{
    private FileProvider configFile;
    private FileProvider langFile;
    private DatabaseImpl database;
    private Table table, random;

    public static StorageManager getInstance()
    {
        return new StorageManager();
    }

    public void setupStorage()
    {
        NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "Loading data...");

        configFile = new FileProvider("config.yml");

        langFile = new FileProvider("lang.yml");

        detectStorage();

        String ip = configFile.getString(Config.STORAGE_MYSQL_IP.getValue()), username = configFile.getString(Config.STORAGE_MYSQL_USER.getValue()), password = configFile.getString(Config.STORAGE_MYSQL_PASSWORD.getValue()), database_name = configFile.getString(Config.STORAGE_TABLE_NAME.getValue()), random_name = configFile.getString(Config.STORAGE_TABLE_RANDOMNAME.getValue());
        int port = configFile.getFileConfiguration().getInt(Config.STORAGE_MYSQL_PORT.getValue());

        RequestHandler requestHandler = new RequestHandler(database);

        if (StorageMode.isMode(StorageMode.SQLITE))
        {

            database = new SQLiteDatabase(configFile.getString(Config.STORAGE_SQLITE_FILENAME.getValue()));

            requestHandler.executeUpdate("CREATE TABLE IF NOT EXISTS `" + configFile.getString(Config.STORAGE_TABLE_NAME.getValue()) + "` (" + "`UUID` TEXT UNIQUE, " + " `NICKED` INTEGER, " + " `NAME` TEXT, " + " `SKIN` TEXT " + ");");

            requestHandler.executeUpdate("CREATE TABLE IF NOT EXISTS `" + configFile.getString(Config.STORAGE_TABLE_RANDOMNAME.getValue()) + "` (" + "`NAME` TEXT" + ");");
        }
        else
        {
            database = new MySQLDatabase(ip,
                                         port,
                                         username,
                                         password,
                                         database_name);

            requestHandler.executeUpdate("CREATE TABLE IF NOT EXISTS `" + configFile.getString(Config.STORAGE_TABLE_NAME.getValue()) + "` (`UUID` VARCHAR(255), `NICKED` tinyint(1), `NAME` VARCHAR(16), `SKIN` VARCHAR(16), UNIQUE (uuid));");

            requestHandler.executeUpdate("CREATE TABLE IF NOT EXISTS `" + configFile.getString(Config.STORAGE_TABLE_RANDOMNAME.getValue()) + "` " + "(`NAME` VARCHAR(16));");
        }

        database.connect();

        table = database.getTable(database_name);
        random = database.getTable(random_name);
    }

    public Table getMainTable()
    {
        return table;
    }

    public Table getRandomNamesTable()
    {
        return random;
    }

    private void detectStorage()
    {
        if (! configFile.getFileConfiguration().getBoolean(Config.STORAGE_COMMON_MYSQL.getValue()))
        {
            StorageMode.setMode(StorageMode.SQLITE);
        }
        else
        {
            StorageMode.setMode(StorageMode.MYSQL);
        }
    }
}
