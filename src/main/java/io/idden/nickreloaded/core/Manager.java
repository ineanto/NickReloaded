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

package io.idden.nickreloaded.core;

import io.idden.nickreloaded.NickReloaded;
import io.idden.nickreloaded.addon.AddonManager;

/**
 * Called on {@link NickReloaded#onEnable()}.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public class Manager
{
    public BukkitVersion bukkitVersion = new BukkitVersion();
    public AddonManager  addonManager  = new AddonManager();

    public void start()
    {
        /*
          This is important to call this as
          soon as the plugin is enabled
          otherwise the plugin can't work,
          as he depends on NMS version to work.
         */
        bukkitVersion.set();

        /*
          Load addons before everything to
          avoid errors.
         */
        addonManager.loadAddons();
    }

    public void onLoad()
    {
    }

    public void stop()
    {
        bukkitVersion.unset();
    }
}
