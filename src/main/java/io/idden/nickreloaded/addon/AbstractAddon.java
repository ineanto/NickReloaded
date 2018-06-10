/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.addon;

/**
 * Represent a plugin hook.
 *
 * @author Antoine "Idden" ROCHAS
 * @since 2.0-rc1
 */
public abstract class AbstractAddon
{
    public String id;
    public String prefix;

    public abstract boolean search();

    public abstract void load();

    public abstract void unload();
}
