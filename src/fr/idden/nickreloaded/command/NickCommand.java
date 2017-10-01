package fr.idden.nickreloaded.command;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.command.ExecutableCommand;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import fr.idden.nickreloaded.api.parser.MinecraftName;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand
        extends ExecutableCommand
{
    private static ConfigFile configFile = NickReloaded.getInstance().getStorageManager().getConfigFile();

    public NickCommand()
    {
        super("nick");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (player.hasPermission("nickreloaded.*") || player.hasPermission("nickreloaded.nick") || player.hasPermission("*"))
            {
                if (args.length < 1)
                {
                    MinecraftName parser = new MinecraftName("default");

                    if (! parser.validate())
                    {
                        sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_INVALIDNAME.getConfigValue(),
                                                                false));
                    }
                    else
                    {
                        NickReloaded.getInstance().getNickManager().nick(player,
                                                                         "default",
                                                                         "default");
                        sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NICK_SUCCESS.getConfigValue(),
                                                                false).replace("%nick%",
                                                                               "default").replace("%skin%",
                                                                                                  "default") + " " + configFile.getString(Config.MESSAGES_COMMANDS_NICK_TOUNNICK.getConfigValue(),
                                                                                                                                true));
                    }

                }
                else
                {
                    if (args[0].equalsIgnoreCase(player.getName()))
                    {
                        sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NICK_OWNNAME.getConfigValue(),
                                                                false));
                    }
                    else
                    {
                        MinecraftName parser = new MinecraftName(args[0]);

                        if (! parser.validate())
                        {
                            sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_INVALIDNAME.getConfigValue(),
                                                                    false));
                        }
                        else
                        {
                            if (args.length == 1)
                            {
                                NickReloaded.getInstance().getNickManager().nick(player,
                                                                                 args[0],
                                                                                 args[0]);
                                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NICK_SUCCESS.getConfigValue(),
                                                                        false).replace("%nick%",
                                                                                       args[0]).replace("%skin%",
                                                                                                        args[0]) + " " + configFile.getString(Config.MESSAGES_COMMANDS_NICK_TOUNNICK.getConfigValue(),
                                                                                                                                              true));
                            }
                            else if (args.length == 2)
                            {
                                {
                                    NickReloaded.getInstance().getNickManager().nick(player,
                                                                                     args[0],
                                                                                     args[1]);
                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NICK_SUCCESS.getConfigValue(),
                                                                            false).replace("%nick%",
                                                                                           args[0]).replace("%skin%",
                                                                                                            args[1]) + " " + configFile.getString(Config.MESSAGES_COMMANDS_NICK_TOUNNICK.getConfigValue(),
                                                                                                                                                  true));
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
        else
        {
            if (args.length < 1)
            {
                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ARGSMISSING.getConfigValue(),
                                                        false));
            }
        }

        return false;
    }
}
