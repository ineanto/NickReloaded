package fr.antoinerochas.nickreloaded.api.string;

public class Messages
{
    public static String SEPARATOR = "§6§m+§7§m--------------§6§m---------------§7§m--------------§6§m+";
    public static String PAPI_HOOK = "§6PlaceholderAPI §afound §6! §aHooked.";
    public static String BUNGEECORD_HOOK = "§6Bungeecord §afound §6! §aHooked.";
    public static String PLUGIN_ENABLED = "§aPlugin enabled.";
    public static String ERROR_UNABLE_TO_GET_ADAPTER_FOR_VERSION = "§cUnable to get an adapter for module %module for version(s): ";

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
