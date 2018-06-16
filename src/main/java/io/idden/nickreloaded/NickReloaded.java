/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded;

import io.idden.nickreloaded.core.Manager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of NickReloaded.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class NickReloaded extends JavaPlugin
{
    public static NickReloaded INSTANCE;

    public final Manager manager = new Manager();

    @Override
    public void onEnable()
    {
        INSTANCE = this;

        manager.start();
    }

    @Override
    public void onDisable()
    {
        manager.stop();
    }
}
