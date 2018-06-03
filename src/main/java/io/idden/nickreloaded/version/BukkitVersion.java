package io.idden.nickreloaded.version;

import io.idden.nickreloaded.NickReloaded;

/**
 * Find and set current version of {@link org.bukkit.Bukkit}.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class BukkitVersion
{
    public static String VERSION_DEFAULT = "none";
    public static String VERSION = VERSION_DEFAULT;

    public BukkitVersion()
    {
        String packageName = NickReloaded.INSTANCE.getServer().getClass().getPackage().getName();

        VERSION = packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public void unset()
    {
        VERSION = VERSION_DEFAULT;
    }
}
