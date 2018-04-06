package fr.antoinerochas.nickreloaded.core.storage;

import java.util.UUID;

public class NRData
{
    private UUID uuid;
    private boolean nicked;
    private String name, skin;

    public NRData() {}

    public NRData(UUID uuid, boolean nicked, String name, String skin)
    {
        this.uuid = uuid;
        this.nicked = nicked;
        this.name = name;
        this.skin = skin;
    }

    public UUID getUUID()
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
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSkin()
    {
        return skin;
    }

    public void setSkin(String skin)
    {
        this.skin = skin;
    }
}
