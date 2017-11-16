package fr.antoinerochas.nickreloaded.api.string;

import fr.antoinerochas.nickreloaded.api.config.Config;
import fr.antoinerochas.nickreloaded.api.manager.StorageManager;

public class MessageBuilder
{
    private String message;

    public MessageBuilder(String message)
    {
        this.message = message;
    }

    public String prefix()
    {
        return StorageManager.getInstance().getConfigFile().getString(Config.PREFIX.getValue());
    }

    public String errorPrefix() { return prefix() + StorageManager.getInstance().getConfigFile().getString(Config.ERROR_PREFIX.getValue()); }

    public String buildError()
    {
        return errorPrefix() + message;
    }

    public String buildNormal()
    {
        return prefix() + message;
    }

    public String buildSuccess()
    {
        return prefix() + "§a" + message;
    }
}
