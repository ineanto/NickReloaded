package fr.idden.nickreloaded.api.manager;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import fr.idden.nickreloaded.api.storage.PlayerStorage;
import fr.idden.nickreloaded.api.storage.RequestHandler;
import fr.idden.nickreloaded.api.storage.StorageMode;
import fr.idden.nickreloaded.api.storage.impl.DatabaseImpl;
import fr.idden.nickreloaded.api.storage.impl.StorageManagerImpl;
import fr.idden.nickreloaded.api.storage.mysql.Field;
import fr.idden.nickreloaded.api.storage.mysql.MySQLDatabase;
import fr.idden.nickreloaded.api.storage.mysql.Table;
import fr.idden.nickreloaded.api.storage.sqlite.SQLiteDatabase;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class StorageManager
        implements StorageManagerImpl
{
    private ConfigFile configFile;
    private DatabaseImpl database;
    private Table table, randomTable;

    public void setupStorage()
    {
        NickReloaded.getInstance().log("§aLoading storage...");

        configFile = new ConfigFile(NickReloaded.getInstance(),
                                    "config.yml");

        detectStorage();

        if (StorageMode.isMode(StorageMode.SQLITE))
        {
            NickReloaded.getInstance().log("§6Using §bSQLite §6!");
            database = new SQLiteDatabase(configFile.getString(Config.DATABASE_SQLITE_FILENAME.getConfigValue(),
                                                               true));
            database.connect();

            RequestHandler requestHandler = new RequestHandler(database);

            requestHandler.executeUpdate("CREATE TABLE IF NOT EXISTS `" + configFile.getString(Config.DATABASE_SQLITE_FILENAME.getConfigValue(),
                                                                                               true) + "` (" + "`uuid` TEXT(16) UNIQUE, " + " `nicked` INTEGER(1), " + " `nick` TEXT(16), " + " `skin` TEXT(16) " + ");");

            requestHandler.executeUpdate("CREATE TABLE IF NOT EXISTS `" + configFile.getString(Config.DATABASE_COMMON_RANDOMNAME.getConfigValue(),
                                                                                               true) + "` (" + "`nick` TEXT" + ");");

            table = database.getTable(configFile.getString(Config.DATABASE_COMMON_NAME.getConfigValue(),
                                                           true));
            randomTable = database.getTable(configFile.getString(Config.DATABASE_COMMON_RANDOMNAME.getConfigValue(),
                                                                 true));

            NickReloaded.getInstance().log("§aLoaded SQLite !");
        }
        else
        {
            NickReloaded.getInstance().log("§6Using §bMySQL §6!");
            database = new MySQLDatabase(configFile.getString(Config.DATABASE_MYSQL_IP.getConfigValue(),
                                                              true),
                                         configFile.getConfigC().getInt(Config.DATABASE_MYSQL_PORT.getConfigValue()),
                                         configFile.getString(Config.DATABASE_COMMON_NAME.getConfigValue(),
                                                              true),
                                         configFile.getString(Config.DATABASE_MYSQL_USER.getConfigValue(),
                                                              true),
                                         configFile.getString(Config.DATABASE_MYSQL_PASSWORD.getConfigValue(),
                                                              true));
            database.connect();

            RequestHandler requestHandler = new RequestHandler(database);

            requestHandler.executeUpdate("CREATE TABLE IF NOT EXISTS `" + configFile.getString(Config.DATABASE_COMMON_NAME.getConfigValue(),
                                                                                              true) + "` (`uuid` VARCHAR(255), `nicked` tinyint(1), `nick` VARCHAR(16),`skin` VARCHAR(16), UNIQUE (uuid))");

            requestHandler.executeUpdate("CREATE TABLE IF NOT EXISTS `" + configFile.getString(Config.DATABASE_COMMON_RANDOMNAME.getConfigValue(),
                                                                                               true) + "` " + "(`nick` VARCHAR(16))");

            table = database.getTable(configFile.getString(Config.DATABASE_COMMON_NAME.getConfigValue(),
                                                           true));
            randomTable = database.getTable(configFile.getString(Config.DATABASE_COMMON_RANDOMNAME.getConfigValue(),
                                                                 true));

            NickReloaded.getInstance().log("§aLoaded MySQL !");
        }

    }

    @Override
    public void load(UUID uuid)
    {
        if (table != null)
        {
            if (! hasAccount(uuid))
            {
                createAccount(uuid);
                load(uuid);
            }
            else
            {
                Field field = new Field("uuid",
                                        uuid);

                String nick = (String) table.select("nick",
                                                    field);
                String skin = (String) table.select("skin",
                                                    field);

                if(StorageMode.isMode(StorageMode.MYSQL))
                {
                    boolean nicked = (boolean) table.select("nicked",
                                                            field);

                    new PlayerStorage(uuid.toString(),
                                      nicked,
                                      nick,
                                      skin);
                }
                else
                {
                    int nicked = (int) table.select("nicked",
                                                    field);

                    new PlayerStorage(uuid.toString(),
                                      nicked == 1,
                                      nick,
                                      skin);
                }
            }

            NickReloaded.getInstance().log("§aDone processing data for '" + Bukkit.getPlayer(uuid).getName() + "' !");
        }
    }

    @Override
    public void save(UUID uuid)
    {
        if (table != null)
        {
            if (! hasAccount(uuid))
            {
                createAccount(uuid);
                save(uuid);
            }
            else
            {
                boolean nicked = PlayerStorage.getStorage(uuid).isNicked();
                String nick = PlayerStorage.getStorage(uuid).getNick();
                String skin = PlayerStorage.getStorage(uuid).getSkin();

                Field field = new Field("uuid",
                                        uuid);

                HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("nicked",
                            nicked ? 1 : 0);
                hashMap.put("nick",
                            nick);
                hashMap.put("skin",
                            skin);

                table.update(hashMap,
                             field);
            }

            NickReloaded.getInstance().log("§aDone sending data for '" + Bukkit.getPlayer(uuid).getName() + "' !");
        }
    }

    @Override
    public void createAccount(UUID uuid)
    {
        if (table != null)
        {
            NickReloaded.getInstance().log("§a'" + Bukkit.getPlayer(uuid).getName() + "' don't have data, creating...");

            HashMap<String, Object> hashMap = new HashMap<>();

            hashMap.put("uuid",
                        uuid.toString());
            hashMap.put("nicked",
                        0);
            hashMap.put("nick",
                        null);
            hashMap.put("skin",
                        null);

            table.insert(hashMap);
        }
    }

    @Override
    public boolean hasAccount(UUID uuid)
    {
        return table.select("uuid",
                            new Field("uuid",
                                      uuid)) != null;
    }

    @Override
    public void detectStorage()
    {
        if (! configFile.getConfigC().getBoolean(Config.DATABASE_COMMON_MYSQL.getConfigValue()))
        {
            StorageMode.setMode(StorageMode.SQLITE);
        }
        else
        {
            StorageMode.setMode(StorageMode.MYSQL);
        }
    }

    public ConfigFile getConfigFile()
    {
        return configFile;
    }
}
