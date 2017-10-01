package fr.idden.nickreloaded.command;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.command.ExecutableCommand;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import fr.idden.nickreloaded.api.parser.MinecraftName;
import fr.idden.nickreloaded.api.storage.PlayerStorage;
import fr.idden.nickreloaded.command.help.HelpValues;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickReloadedCommand
        extends ExecutableCommand
{
    private ConfigFile configFile = NickReloaded.getInstance().getStorageManager().getConfigFile();

    public NickReloadedCommand()
    {
        super("nickreloaded");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (player.hasPermission("nickreloaded.*") || player.hasPermission("nickreloaded.nickreloaded") || player.hasPermission("*"))
            {
                if (args.length < 1)
                {
                    sender.sendMessage("§7§m----------§6§m------------------------------§7§m----------§r\n");
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_TITLE.getConfigValue(),
                                                            true));
                    for (HelpValues command : HelpValues.values())
                    {
                        sender.sendMessage("§7- §6/" + command.getCommand() + " §7• §b" + configFile.getString(command.getDescription(),
                                                                                                               true));
                    }

                    sender.sendMessage(" ");

                    sender.sendMessage("§aLink: https://www.spigotmc.org/resources/nickreloaded.46335/");
                    sender.sendMessage("§a© Idden 2017.");

                    sender.sendMessage("§7§m----------§6§m------------------------------§7§m----------§r");
                }
                else if (args[0].equalsIgnoreCase("reload"))
                {
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_RELOADING.getConfigValue(),
                                                            false));
                    configFile.load();
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_DONERELOADED.getConfigValue(),
                                                            false));
                }
                else if (args[0].equalsIgnoreCase("check"))
                {
                    if (args.length < 2)
                    {
                        sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_ARGSMISSING.getConfigValue(),
                                                                false));
                    }
                    else
                    {

                        MinecraftName parser = new MinecraftName(args[1]);

                        if (! parser.validate())
                        {
                            sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_INVALIDNAME.getConfigValue(),
                                                                    false).replace("%name%",
                                                                                   args[1]));
                        }
                        else
                        {
                            Player target = Bukkit.getPlayer(args[1]);

                            if (target == null || ! target.isOnline())
                            {
                                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_OFFLINEPLAYER.getConfigValue(),
                                                                        false).replace("%name%",
                                                                                       args[1]));
                            }
                            else
                            {
                                sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_MAIN_STATUS.getConfigValue(),
                                                                        false).replace("%name%",
                                                                                       target.getName()).replace("%status%",
                                                                                                                 PlayerStorage.getStorage(target.getUniqueId()).isNicked() ? "&a✔" : "&c✕").replace("&",
                                                                                                                                                                                                    "§"));
                            }
                        }
                    }
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_NOPERMISSION.getConfigValue(),
                                                            false));
                }
                else
                {
                    sender.sendMessage(configFile.getString(Config.MESSAGES_COMMANDS_UNKNOWNCOMMAND.getConfigValue(),
                                                            false));
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
