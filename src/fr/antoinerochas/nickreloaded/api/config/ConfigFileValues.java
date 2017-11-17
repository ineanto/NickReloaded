package fr.antoinerochas.nickreloaded.api.config;

public enum ConfigFileValues
{
    BUNGEECORD(false),
    STORAGE_COMMON_MYSQL(false),
    STORAGE_COMMON_REDIS(false),
    STORAGE_TABLE_NAME("nickreloaded"),
    STORAGE_TABLE_RANDOMNAME("randomnames"),
    STORAGE_SQLITE_FILENAME("nickreloaded"),
    STORAGE_MYSQL_IP("127.0.0.1"),
    STORAGE_MYSQL_PORT(3306),
    STORAGE_MYSQL_USER("root"),
    STORAGE_MYSQL_PASSWORD(""),
    STORAGE_REDIS_IP("127.0.0.1"),
    STORAGE_REDIS_PORT(6379),
    STORAGE_REDIS_PASSWORD(""),


    PREFIX("§6NickReloaded §f§l⎜ "),
    ERROR_PREFIX("§c§lERROR §f§l⎜ §c");

    public Object value;

    ConfigFileValues(Object value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return name().replace("_", ".").toLowerCase();
    }
}
