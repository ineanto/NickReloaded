package fr.antoinerochas.nickreloaded.core.storage.mysql;

/**
 * @author <a href="mailto:contact@anana.xyz">Anana</a>
 */
public class Field
{

    private String name;
    private Object value;

    public Field(String name, Object value)
    {
        this.name = name;
        this.value = value;
    }

    /**
     * Retrieves the parsed name with the simple quotes.
     *
     * @return The parsed name with the simple quotes.
     */
    public String getParsedName()
    {
        return " `" + name + "` ";
    }

    /**
     * Retrieves the parsed value with the simple quotes.
     *
     * @return The parsed value with the simple quotes.
     */
    public String getParsedValue()
    {
        return " '" + value + "' ";
    }

    /**
     * Retrieves the parsed name and values with
     * simple quotes and equal.
     *
     * @return The parsed name and values with simple quotes and equal.
     */
    public String getParsedNameAndValue()
    {
        return getParsedName() + "=" + getParsedValue();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }
}
