package fr.idden.nickreloaded;

import fr.idden.nickreloaded.api.nms.PayloadManager;

public enum Messages
{
    SEPARATOR("§b-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"),
    PLACEHOLDERAPI_FOUND("§bPlaceholderAPI §afound §b! Hooked."),
    BUNGEECORD_FOUND("§bBungeecord §aenabled §b!"),
    PLUGIN_ENABLED("§a§mPlugin enabled."),
    ERROR_DISABLED_PLUGIN("§4Disabling plugin :"),
    ERROR_PAYLOAD_UNABLE_TO_GET_ADAPTER_FOR_VERSION("§cPayload was unable to get an adapter for module %module for version(s): " + PayloadManager.vD());

    private String message;

    Messages(String message)
    {
        this.message = message;
    }

    public String m()
    {
        return message;
    }
}
