package io.idden.nickreloaded.command.abstrct;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;

public abstract class AbstractCommand extends Command
{
    public AbstractCommand(String name)
    {
        super(name);

        CommandMap commandMap      = null;
        Field      commandMapField = null;

        try
        {
            commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }

        commandMapField.setAccessible(true);

        try
        {
            commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        commandMap.register(name, this);
    }

    @Override
    public abstract boolean execute(CommandSender sender, String s, String[] strings);
}
