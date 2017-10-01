package fr.idden.nickreloaded.command;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.command.ExecutableCommand;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnnickCommand extends ExecutableCommand
{
    private ConfigFile configFile = NickReloaded.getInstance().getStorageManager().getConfigFile();

    public UnnickCommand()
    {
        super("unnick");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;

            if (player.hasPermission("nickreloaded.*") || player.hasPermission("nickreloaded.unnick") || player.hasPermission("*"))
            {
                if(NickReloaded.getInstance().getNickManager().isNicked(player))
                {
                    NickReloaded.getInstance().getNickManager().nick(player, null, null);
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_UNNICK_SUCCESS.getConfigValue(), false));
                }
                else
                {
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_UNNICK_NOTNICKED.getConfigValue(), false));
                }
            }
            else
            {
                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NOPERMISSION.getConfigValue(), false));
            }
        }

        return false;
    }
}
