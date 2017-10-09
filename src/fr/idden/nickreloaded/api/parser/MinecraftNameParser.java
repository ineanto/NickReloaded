package fr.idden.nickreloaded.api.parser;

import java.util.regex.Pattern;

public class MinecraftNameParser
{
    private String pattern;

    public MinecraftNameParser(String pattern)
    {
        this.pattern = pattern;
    }

    public boolean validate()
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_]");
        return ! pattern.matcher(this.pattern).find() && this.pattern.length() <= 16;
    }
}
