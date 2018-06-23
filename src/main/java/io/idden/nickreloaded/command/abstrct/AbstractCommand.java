/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.command.abstrct;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.logger.Logger;
import org.apache.commons.lang3.Validate;
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

        Field      commandMapField;
        CommandMap commandMap;

        try
        {
            commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            Validate.notNull(commandMapField, "CommandMapField not found");
            commandMapField.setAccessible(true);

            commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            Validate.notNull(commandMap, "Unable to get CommandMap");
            commandMap.register(name, this);
            NickReloaded.INSTANCE.manager.logger.log(Logger.Level.DEBUG, "Registered \"" + name + "\" command.");
        }
        catch (IllegalAccessException | NoSuchFieldException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public abstract boolean execute(CommandSender sender, String s, String[] strings);
}
