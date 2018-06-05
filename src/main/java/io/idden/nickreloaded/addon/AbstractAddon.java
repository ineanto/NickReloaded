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

    public abstract void register();

    public abstract void unregister();
}
