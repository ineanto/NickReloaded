/*
 *  MIT License
 *
 *  Copyright (c) 2017-2018 Antoine "Idden" ROCHAS
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

package io.idden.nickreloaded.listener;

import io.idden.nickreloaded.NickReloaded;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * Manage {@link Listener}s
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class ListenerManager
{
    public ArrayList<Listener> LISTENERS = new ArrayList<>();

    public ListenerManager()
    {
        LISTENERS.add(new PlayerJoinListener());
    }

    public void registerListeners()
    {
        LISTENERS.forEach(listener -> NickReloaded.INSTANCE.getServer().getPluginManager().registerEvents(listener, NickReloaded.INSTANCE));
    }

    public void unregisterListeners()
    {
        LISTENERS.clear();
    }
}
