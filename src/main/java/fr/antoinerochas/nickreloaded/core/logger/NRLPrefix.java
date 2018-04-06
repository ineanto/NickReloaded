package fr.antoinerochas.nickreloaded.core.logger;

/**
 * Enum used to list all the differents prefixes used in {@link NRLogger} logs.
 *
 * @author Antoine "Idden" R.
 * @since 2.0-rc1
 */
public enum NRLPrefix
{
    NONE(""),
    INFO("§aINFO"),
    WARN("§6WARN"),
    ERROR("§cERROR"),
    FATAL("§4FATAL");

    private String level;

    NRLPrefix(String level)
    {
        this.level = " | " + level + "§r | ";
    }

    public String getPrefix()
    {
        return level;
    }
}
