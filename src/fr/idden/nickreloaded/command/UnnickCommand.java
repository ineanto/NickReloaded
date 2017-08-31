package fr.idden.nickreloaded.command;

import fr.idden.nickreloaded.api.command.ExecutableCommand;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import fr.idden.nickreloaded.api.nick.NickManager;
import fr.idden.nickreloaded.api.storage.StorageManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnnickCommand extends ExecutableCommand
{
    private ConfigFile configFile = StorageManager.getConfigFile();

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

            if (! sender.isOp() || ! sender.hasPermission("nickreloaded.unnick") || ! sender.hasPermission("nickreloaded.*") || ! sender.hasPermission("*"))
            {
                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NOPERMISSION.getConfigValue(), false));
            }
            else
            {
                if(NickManager.isNicked(player))
                {
                    NickManager.nick(player, null, null);
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_UNNICK_SUCCESS.getConfigValue(), false));
                }
                else
                {
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_UNNICK_NOTNICKED.getConfigValue(), false));
                }
            }
        }

        return false;
    }
}
