package fr.idden.nickreloaded.api.storage;

import fr.idden.nickreloaded.api.data.NickData;
import fr.idden.nickreloaded.api.logger.NickReloadedLogger;
import fr.idden.nickreloaded.api.manager.StorageManager;
import fr.idden.nickreloaded.api.storage.mysql.Field;
import fr.idden.nickreloaded.api.storage.mysql.Table;

import java.util.HashMap;
import java.util.UUID;

public class AccountProvider
{
    private UUID uuid;
    private Field fuuid;

    //USED ONLY IF REDIS IS NOT USED !
    private HashMap<UUID, NickData> DATA = new HashMap<>();

    public AccountProvider(UUID uuid)
    {
        this.uuid = uuid;
        this.fuuid = new Field("UUID",
                               uuid);
    }

    public NickData getAccount()
    {
        NickData nickData = getDataFromCache();

        if (nickData == null)
        {
            nickData = getDataFromStorage();

            //send data to redis (if enabled)
        }

        return nickData;
    }

    public NickData getDataFromStorage()
    {
        Table main = StorageManager.getInstance().getMainTable();

        boolean account = main.select(fuuid.getName(),
                                      fuuid) != null;

        if(! account)
        {
            //CREATE ACCOUNT
            HashMap<String, Object> FIELDS = new HashMap<>();

            FIELDS.put("UUID", fuuid.getValue());
            FIELDS.put("NICKED", 0);
            FIELDS.put("NAME", null);
            FIELDS.put("SKIN", null);

            main.insert(FIELDS);

            NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "Created new account for UUID: '" + uuid + "'.");
        }

        boolean nicked = (boolean) main.select("NICKED", fuuid);
        String name = (String) main.select("NAME", fuuid);
        String skin = (String) main.select("SKIN", fuuid);

        return new NickData(uuid,
                            nicked,
                            name,
                            skin);
    }

    public NickData getDataFromCache()
    {
        return new NickData(uuid,
                            false,
                            "TEST",
                            "TEST");
    }
}
