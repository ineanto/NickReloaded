package fr.idden.nickreloaded;

public enum Messages
{
    SEPARATOR("§b-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"),
    PLACEHOLDERAPI_FOUND("§bPlaceholderAPI §afound §b! Hooked."),
    BUNGEECORD_FOUND("§bBungeecord §afound §b! Hooked."),
    PLUGIN_ENABLED("§a§mPlugin enabled."),
    ERROR_DISABLED_PLUGIN("§4Disabling plugin :"),
    ERROR_PAYLOAD_UNABLE_TO_GET_ADAPTER_FOR_VERSION("§cPayload was unable to get an adapter for module %module for version(s): " + NickReloaded.getInstance().getPayloadManager().getBukkitVersion());

    private String message;

    Messages(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
