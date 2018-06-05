package io.idden.nickreloaded.core;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.addon.AddonManager;
import io.idden.nickreloaded.version.BukkitVersion;

/**
 * Called on {@link NickReloaded#onEnable()}.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Manager
{
    public BukkitVersion bukkitVersion = new BukkitVersion();
    public AddonManager  addonManager  = new AddonManager();

    public void start()
    {
        /*
          This is important to call this as
          soon as the plugin is enabled
          otherwise the plugin can't work,
          as he depends on NMS version to work.
         */
        bukkitVersion.set();

        /*
          Load addons before everything to
          avoid errors.
         */
        addonManager.loadAddons();
    }

    public void onLoad()
    {
    }

    public void stop()
    {
        bukkitVersion.unset();
    }
}
