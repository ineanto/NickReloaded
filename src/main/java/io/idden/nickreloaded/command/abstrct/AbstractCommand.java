/*
 * MIT License
 *
 * Copyright (c) 2017 Antoine "Idden" ROCHAS
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
