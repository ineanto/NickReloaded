package fr.idden.nickreloaded.command;

import fr.idden.nickreloaded.api.command.ExecutableCommand;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import fr.idden.nickreloaded.api.nick.NickManager;
import fr.idden.nickreloaded.api.storage.StorageManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminNickCommand extends ExecutableCommand
{
    private ConfigFile configFile = StorageManager.getConfigFile();

    public AdminNickCommand()
    {
        super("admnick");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;

            if(! sender.isOp() || ! sender.hasPermission("nickreloaded.adminnick") || ! sender.hasPermission("nickreloaded.*"))
            {
                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NOPERMISSION.getConfigValue(), false));
            }
            else
            {
                if(args.length < 1)
                {
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ARGSMISSING.getConfigValue(), false));
                }
                else
                {
                    if(args[0].equalsIgnoreCase("unnick"))
                    {
                        if(args.length < 2)
                        {
                            sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ARGSMISSING.getConfigValue(), false));
                        }
                        else
                        {
                            if(args[1].equalsIgnoreCase("all"))
                            {
                                if(NickManager.players.isEmpty())
                                {
                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ADMNICK_UNNICKALLFAILED.getConfigValue(), false));
                                }
                                else
                                {
                                    for (Player uPlayer : NickManager.players)
                                    {
                                        NickManager.nick(uPlayer,
                                                         null, null);
                                        NickManager.stopTask(uPlayer);
                                    }

                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ADMNICK_UNNICKALLSUCCESS.getConfigValue(),
                                                                            false));
                                }
                            }
                            else if(args[1].equalsIgnoreCase("asideme"))
                            {
                                if(NickManager.players.contains(player) && NickManager.players.size() == 1)
                                {
                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ADMNICK_UNNICKALLFAILED.getConfigValue(), false));
                                }
                                else
                                {
                                    for(Player uPlayer : NickManager.players)
                                    {
                                        if(uPlayer != player)
                                        {
                                            NickManager.nick(uPlayer, null, null);
                                            NickManager.stopTask(uPlayer);
                                        }
                                    }

                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ADMNICK_UNNICKALLSUCCESS.getConfigValue(), false));
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}