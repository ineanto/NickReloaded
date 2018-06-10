/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.command;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.command.abstrct.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The "/nick" command class.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class NickCommand extends AbstractCommand
{
    public NickCommand(String name)
    {
        super(name);
        setPermission("nickreloaded.nick");
        //setPermissionMessage()
    }

    @Override
    public boolean execute(CommandSender sender, String command, String[] args)
    {
        if (! testPermission(sender)) { return true; }

        if(sender instanceof Player)
        {
            return true;
        }
        else
        {
            NickReloaded.INSTANCE.manager.logger.log("Console", "You must me a player to do this !");
            return true;
        }
    }
}
