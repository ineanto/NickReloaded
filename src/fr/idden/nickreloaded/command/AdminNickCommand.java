package fr.idden.nickreloaded.command;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.command.ExecutableCommand;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminNickCommand
        extends ExecutableCommand
{
    private ConfigFile configFile = NickReloaded.get().getStorageManager().getConfigFile();

    public AdminNickCommand()
    {
        super("admnick");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (player.hasPermission("nickreloaded.*") || player.hasPermission("nickreloaded.adminnick") || player.hasPermission("*"))
            {
                if (args.length < 1)
                {
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ARGSMISSING.getConfigValue(),
                                                            false));
                }
                else
                {
                    if (args[0].equalsIgnoreCase("unnick"))
                    {
                        if (args.length < 2)
                        {
                            sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ARGSMISSING.getConfigValue(),
                                                                    false));
                        }
                        else
                        {
                            if (args[1].equalsIgnoreCase("all"))
                            {
                                if (NickReloaded.get().getNickManager().getPlayers().isEmpty())
                                {
                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ADMNICK_UNNICKALLFAILED.getConfigValue(),
                                                                            false));
                                }
                                else
                                {
                                    for (Player uPlayer : NickReloaded.get().getNickManager().getPlayers())
                                    {
                                        NickReloaded.get().getNickManager().nick(uPlayer,
                                                                                 null,
                                                                                 null);
                                    }

                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ADMNICK_UNNICKALLSUCCESS.getConfigValue(),
                                                                            false));
                                }
                            }
                            else if (args[1].equalsIgnoreCase("asideme"))
                            {
                                if (NickReloaded.get().getNickManager().getPlayers().contains(player) && NickReloaded.get().getNickManager().getPlayers().size() == 1)
                                {
                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ADMNICK_UNNICKALLFAILED.getConfigValue(),
                                                                            false));
                                }
                                else
                                {
                                    for (Player nPlayer : NickReloaded.get().getNickManager().getPlayers())
                                    {
                                        if (nPlayer != player)
                                        {
                                            NickReloaded.get().getNickManager().nick(nPlayer,
                                                                                     null,
                                                                                     null);
                                        }
                                    }

                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ADMNICK_UNNICKALLSUCCESS.getConfigValue(),
                                                                            false));
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NOPERMISSION.getConfigValue(),
                                                        false));
            }
        }
        return false;
    }
}