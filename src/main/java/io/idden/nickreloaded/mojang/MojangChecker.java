/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.mojang;

import org.shanerx.mojang.Mojang;

/**
 * Check everything is OK Mojang side, to be able to change skin later.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class MojangChecker
{
    public boolean check()
    {
        return new Mojang().connect().getStatus(Mojang.ServiceType.SKINS_MINECRAFT_NET) == Mojang.ServiceStatus.GREEN;
    }
}
