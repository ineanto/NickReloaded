package fr.idden.nickreloaded.api.storage;

import java.util.ArrayList;
import java.util.Random;

public class RandomNameStorage
{
    private String name;

    private static ArrayList<String> names = new ArrayList<>();

    public RandomNameStorage(String name)
    {
        if(! names.contains(name))
        {
            names.add(name);
        }
    }

    public static String getRandomName()
    {
        if(! names.isEmpty())
        {
            Random random = new Random();

            return names.get(random.nextInt(names.size()));
        }

        return "default";
    }
}
