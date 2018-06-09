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

package io.idden.nickreloaded.addon.papi;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.addon.AbstractAddon;
import io.idden.nickreloaded.addon.result.AddonRegisterResult;
import io.idden.nickreloaded.logger.Logger;

/**
 * Register {@link me.clip.placeholderapi.PlaceholderAPI} addon.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class PlaceholderAPIAddon extends AbstractAddon
{
    PlaceholderAPIExpansion placeholderAPIExpansion = new PlaceholderAPIExpansion();

    public PlaceholderAPIAddon()
    {
        this.id = "PlaceholderAPI-Addon";
    }

    @Override
    public void register(AddonRegisterResult registerResult)
    {
        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.LOG, "Searching for PlaceholderAPI...");

        if(NickReloaded.INSTANCE.getServer().getPluginManager().getPlugin("PlaceholderAPI") == null)
        {
            NickReloaded.INSTANCE.manager.logger.log(Logger.Level.LOG, "PlaceholderAPI not found ! Skipping.");
            registerResult.notFound();
            return;
        }

        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.LOG, "PlaceholderAPI found ! Hooking...");

        if (placeholderAPIExpansion.register())
        {
            NickReloaded.INSTANCE.manager.logger.log(Logger.Level.LOG, "Hooked ! You can now use NickReloaded placeholders !");
            NickReloaded.INSTANCE.manager.logger.log(Logger.Level.LOG, id + " loaded.");
            registerResult.onSuccess();
            return;
        }

        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.WARNING, "PlaceholderAPI failed to hook, please send an error report.");
        registerResult.onFail();
    }

    @Override
    public void unregister()
    {
        NickReloaded.INSTANCE.manager.logger.log(Logger.Level.LOG, id + " unloaded.");
    }
}
