/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.command.debug;

import io.idden.nickreloaded.NickReloadedConstants;
import io.idden.nickreloaded.command.abstrct.AbstractCommand;
import io.idden.nickreloaded.player.CustomPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class description
 *
 * @author Antoine "Idden" ROCHAS
 * @since 0.X-aX
 */
public class DebugCommand extends AbstractCommand
{
    public DebugCommand()
    {
        super("debug");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            CustomPlayer customPlayer = new CustomPlayer(player);

            player.sendMessage("### START OF DEBUG ###");

            if(args.length < 1)
            {
                customPlayer.debugUtils.sendDebug("Missing command arguments.");
            }
            else
            {
                if(args[0].equalsIgnoreCase("update"))
                {
                    if(args.length < 2)
                    {
                        NickReloadedConstants.WRAPPER.updatePlayer(customPlayer.bukkitPlayer, false);
                    }
                    else
                    {
                        if(args[1].equalsIgnoreCase("true"))
                        {
                            NickReloadedConstants.WRAPPER.updatePlayer(customPlayer.bukkitPlayer, true);
                        }
                    }
                }
            }

            player.sendMessage("### END OF DEBUG ###");
        }
        return false;
    }
}
