/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.command;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.command.abstrct.AbstractCommand;
import io.idden.nickreloaded.player.CustomPlayer;
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
    public NickCommand()
    {
        super("nick");
        setPermission("nickreloaded.nick");
        setUsage("/nick <nickname> [skin]");
    }

    @Override
    public boolean execute(CommandSender sender, String command, String[] args)
    {
        if (! testPermission(sender)) { return true; }

        if (sender instanceof Player)
        {
            Player       player       = (Player) sender;
            CustomPlayer customPlayer = new CustomPlayer(player);

            if (args.length < 1)
            {
                sendHelp(player);
                return true;
            }
            else
            {
                String nickname = args[0];
                String skin     = null;

                if (args.length > 1)
                {
                    skin = args[1];
                }

                player.sendMessage("§cTrying to set your nickname to \"" + nickname + "\"" + (skin == null ? "" : " and your skin to \"" + skin + "\" ") + "...");

                customPlayer.debugUtils.sendDebug("DisguisedBfrNick=" + customPlayer.apparence.disguised);
                customPlayer.apparence.setApparence(nickname, skin);
                customPlayer.debugUtils.sendDebug("Disguised=" + customPlayer.apparence.disguised);

                player.sendMessage("§aNick set!");
                return true;
            }
        }
        else
        {
            NickReloaded.INSTANCE.manager.logger.log("Console", "You must me a player to do this!");
            return true;
        }
    }

    private void sendHelp(Player player)
    {
        player.sendMessage("§cMissing arguments: " + getUsage() + ".");
    }
}
