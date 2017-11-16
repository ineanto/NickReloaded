package fr.antoinerochas.nickreloaded.api.parser;

import java.util.regex.Pattern;

public class MinecraftNameParser
{
    public static boolean validate(String name)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_]");
        return ! pattern.matcher(name).find() && name.length() <= 16;
    }
}
