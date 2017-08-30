package fr.idden.nickreloaded.api.config;

public enum Config
{
    DATABASE_COMMON_USINGMYSQL(false),
    DATABASE_COMMON_NAME("nickreloaded"),
    DATABASE_COMMON_RANDOMNAME("randomnames"),
    DATABASE_SQLITE_FILENAME("nickreloaded"),
    DATABASE_MYSQL_IP("127.0.0.1"),
    DATABASE_MYSQL_PORT(3306),
    DATABASE_MYSQL_USER("root"),
    DATABASE_MYSQL_PASSWORD(""),

    PREFIX("&f[&6NickReloaded&f] "),


    DESCRIPTION_MAIN("Shows help <reload config files, check a player status>."),
    DESCRIPTION_NICK("Gives you a nickname."),
    DESCRIPTION_UNNICK("Removes your nickname."),
    DESCRIPTION_ADMNICK("Manage all the players. (unnick all, etc...)"),

    MESSAGES_COMMANDS_MAIN_TITLE("&6NickReloaded's commands:"),
    MESSAGES_COMMANDS_MAIN_RELOADING("&aReloading config file..."),
    MESSAGES_COMMANDS_MAIN_DONERELOADED("&aConfig file reloaded !"),
    MESSAGES_COMMANDS_MAIN_INCORRECTPLAYER("&c'&l%name%' &cis not a valid name !"),
    MESSAGES_COMMANDS_MAIN_INVALIDNAME("&cThis name contains invalid charcaters/is too long &7(must be < 16)"),
    MESSAGES_COMMANDS_MAIN_OFFLINEPLAYER("&c'%name%' is offline, click here to check into database."),
    MESSAGES_COMMANDS_MAIN_HOVERTEXT("&6Click to check in the database."),
    MESSAGES_COMMANDS_MAIN_STATUS("&aPlayer '%name%' is currently nicked : %status%"),

    MESSAGES_COMMANDS_NICK_ACTIVE("&fYou are currently &c&l&nNICKED"),
    MESSAGES_COMMANDS_NICK_NONAME("&cPlease type a name !"),
    MESSAGES_COMMANDS_NICK_SUCCESS("&6Successfully nicked to &b%nick% &6with &b%skin%&6's skin !"),
    MESSAGES_COMMANDS_NICK_TOUNNICK("&7(Type '/unnick' to unnick.)"),
    MESSAGES_COMMANDS_NICK_ALREADYNICKED("&cYou are already nicked !"),
    MESSAGES_COMMANDS_NICK_OWNNAME("&cYou can't nick with your name !"),

    MESSAGES_COMMANDS_UNNICK_SUCCESS("&aSuccessfully unnicked !"),
    MESSAGES_COMMANDS_UNNICK_NOTNICKED("&cYou are not nicked !"),

    MESSAGES_COMMANDS_ADMNICK_UNNICKALLSUCCESS("&aYou unnicked all the nicked players with success."),
    MESSAGES_COMMANDS_ADMNICK_UNNICKALLFAILED("&cThere is no players to unnick !"),

    MESSAGES_COMMANDS_ARGSMISSING("&cArguments are missing !"),
    MESSAGES_COMMANDS_NOPERMISSION("&cYou can't execute this command !"),
    MESSAGES_COMMANDS_UNKNOWNCOMMAND("&cSubcommand unknown, see help: /nickreloaded !");

    public Object value;

    Config(Object value)
    {
        this.value = value;
    }

    public String getConfigValue()
    {
        return name().replace("_", ".").toLowerCase();
    }
}
