package fr.antoinerochas.nickreloaded.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;

public abstract class ExecutableCommand extends Command
{
    protected ExecutableCommand(String name)
    {
        super(name);

        CommandMap commandMap = null;
        Field f = null;
        try
        {
            f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }

        f.setAccessible(true);

        try
        {
            commandMap = (CommandMap) f.get(Bukkit.getServer());
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        commandMap.register(name,
                            this);
    }

    @Override
    public abstract boolean execute(CommandSender sender, String s, String[] strings);
}
