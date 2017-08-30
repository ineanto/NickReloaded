package fr.idden.nickreloaded.api.storage;

import java.util.HashMap;
import java.util.UUID;

public class PlayerStorage
{
    private String uuid;
    private boolean nicked;
    private String nick, skin;

    private static HashMap<String, PlayerStorage> storageHashMap = new HashMap<>();

    public PlayerStorage(String uuid, boolean nicked, String nick, String skin)
    {
        this.uuid = uuid;
        this.nicked = nicked;
        this.nick = nick;
        this.skin = skin;
        storageHashMap.putIfAbsent(uuid, this);
    }

    //Default getters and setters

    public String getUUID()
    {
        return uuid;
    }

    public boolean isNicked()
    {
        return nicked;
    }

    public void setNicked(boolean nicked)
    {
        this.nicked = nicked;
        storageHashMap.remove(uuid);
        storageHashMap.put(uuid, this);
    }

    public String getNick()
    {
        return nick;
    }

    public void setNick(String nick)
    {
        this.nick = nick;
        storageHashMap.remove(uuid);
        storageHashMap.put(uuid, this);
    }

    public String getSkin()
    {
        return skin;
    }

    public void setSkin(String skin)
    {
        this.skin = skin;
        storageHashMap.remove(uuid);
        storageHashMap.put(uuid, this);
    }

    public static PlayerStorage getStorage(UUID uuid)
    {
        return storageHashMap.get(uuid.toString());
    }
}
