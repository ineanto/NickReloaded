package fr.idden.nickreloaded.command;

import fr.idden.nickreloaded.api.command.ExecutableCommand;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import fr.idden.nickreloaded.api.nick.NickManager;
import fr.idden.nickreloaded.api.parser.MinecraftName;
import fr.idden.nickreloaded.api.storage.RandomNameStorage;
import fr.idden.nickreloaded.api.storage.StorageManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand
        extends ExecutableCommand
{
    private static ConfigFile configFile = StorageManager.getConfigFile();

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
                    String name = RandomNameStorage.getRandomName();

                    MinecraftName parser = new MinecraftName(name);

                    if (! parser.validate())
                    {
                        sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_INVALIDNAME.getConfigValue(),
                                                                false));
                    }
                    else
                    {
                        NickManager.nick(player,
                                         name,
                                         name);
                        sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NICK_SUCCESS.getConfigValue(),
                                                                false).replace("%nick%",
                                                                               name).replace("%skin%", name) + " " + configFile.getString(Config.MESSAGES_COMMANDS_NICK_TOUNNICK.getConfigValue(),
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
                                NickManager.nick(player,
                                                 args[0],
                                                 args[0]);
                                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NICK_SUCCESS.getConfigValue(),
                                                                        false).replace("%nick%",
                                                                                       args[0]).replace("%skin%", args[0]) + " " + configFile.getString(Config.MESSAGES_COMMANDS_NICK_TOUNNICK.getConfigValue(),
                                                                                                                                                        true));
                            }
                            else if (args.length == 2)
                            {
                                {
                                    NickManager.nick(player,
                                                     args[0],
                                                     args[1]);
                                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NICK_SUCCESS.getConfigValue(),
                                                                            false).replace("%nick%",
                                                                                           args[0]).replace("%skin%", args[1]) + " " + configFile.getString(Config.MESSAGES_COMMANDS_NICK_TOUNNICK.getConfigValue(),
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
            //console command
        }

        return false;
    }
}
