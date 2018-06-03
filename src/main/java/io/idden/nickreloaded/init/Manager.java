package io.idden.nickreloaded.init;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.version.BukkitVersion;

/**
 * Called on {@link NickReloaded#onEnable()}.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Manager
{
    public void start()
    {
        /*
          This is important to call this as
          soon as the plugin is enabled
          otherwise the plugin can't work,
          as he depends on NMS version to work.
         */
        new BukkitVersion();
    }

    public void onLoad()
    {
    }

    public void stop()
    {
        new BukkitVersion().unset();
    }
}
