package fr.idden.nickreloaded.api.string;

public class MessageBuilder
{
    private String message;

    public MessageBuilder(String message)
    {
        this.message = message;
    }

    public String prefix()
    {
        return "§6NickReloaded §f§l⎜ ";
    }

    public String buildError()
    {
        return prefix() + "§c§lERROR §f§l⎜ §c" + message;
    }

    public String buildNormal()
    {
        return prefix() + message;
    }

    public String buildSuccess()
    {
        return prefix() + "§a" + message;
    }
}
