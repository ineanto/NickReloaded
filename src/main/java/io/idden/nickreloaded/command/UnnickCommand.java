/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.command;

import io.idden.nickreloaded.command.abstrct.AbstractCommand;
import io.idden.nickreloaded.player.CustomPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Unnick the player.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class UnnickCommand extends AbstractCommand
{
    public UnnickCommand()
    {
        super("unnick");
        setPermission("nickreloaded.unnick");
        setUsage("/unnick [player]");
    }

    @Override
    public boolean execute(CommandSender sender, String command, String[] args)
    {
        if (! testPermission(sender)) { return true; }

        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (args.length < 1)
            {
                CustomPlayer customPlayer = new CustomPlayer(player);

                if(customPlayer.apparence.disguised)
                {
                    customPlayer.apparence.reset();
                }
                else
                {
                    player.sendMessage("§cYou're not nicked!");
                }

                return true;
            }
            else
            {
                player.sendMessage("Command ran on other player!");
            }
        }
        return false;
    }

    private void sendHelp(Player player)
    {
        player.sendMessage("§cMissing arguments: " + getUsage() + ".");
    }
}
