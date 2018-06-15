/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.mojang;

import io.idden.nickreloaded.NickReloaded;
import org.shanerx.mojang.Mojang;

/**
 * Check everything is OK Mojang side, to call skin textures.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class MojangChecker
{
    public boolean checkEverithingIsAllright()
    {
        Mojang mojang = new Mojang().connect();

        if (mojang.getStatus(Mojang.ServiceType.SESSIONSERVER_MOJANG_COM) != Mojang.ServiceStatus.GREEN)
        {
            NickReloaded.INSTANCE.manager.logger.log("ERROR", "The Session Server isn't available right now ! Go to https://status.mojang.com/check for more informations.");
            return false;
        }

        return true;
    }
}
