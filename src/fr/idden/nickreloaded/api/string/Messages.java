package fr.idden.nickreloaded.api.string;

public class Messages
{
    public static String SEPARATOR = "§6§m+§7§m--------------§6§m---------------§7§m--------------§6§m+";
    public static String PAPI_HOOK = "PlaceholderAPI §afound §b! Hooked.";
    public static String BUNGEECORD_HOOK = "Bungeecord §afound §b! Hooked.";
    public static String PLUGIN_ENABLED = "Plugin enabled.";
    public static String ERROR_DISABLED_PLUGIN = "Disabling plugin :";
    public static String ERROR_UNABLE_TO_GET_ADAPTER_FOR_VERSION = "Unable to get an adapter for module %module for version(s): ";

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
