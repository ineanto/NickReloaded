package fr.antoinerochas.nickreloaded.api.manager;

import fr.antoinerochas.nickreloaded.api.nms.v1_11_R1.v1_11_R1_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.api.logger.NickReloadedLogger;
import fr.antoinerochas.nickreloaded.api.nms.impl.AbstractActionbar;
import fr.antoinerochas.nickreloaded.api.nms.impl.AbstractGameprofileFiller;
import fr.antoinerochas.nickreloaded.api.nms.impl.AbstractPlayerIdentityManager;
import fr.antoinerochas.nickreloaded.api.nms.v1_11_R1.v1_11_R1_Actionbar;
import fr.antoinerochas.nickreloaded.api.nms.v1_11_R1.v1_11_R1_GameprofileFiller;
import fr.antoinerochas.nickreloaded.api.nms.v1_12_R1.v1_12_R1_Actionbar;
import fr.antoinerochas.nickreloaded.api.nms.v1_12_R1.v1_12_R1_GameprofileFiller;
import fr.antoinerochas.nickreloaded.api.nms.v1_12_R1.v1_12_R1_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R1.v1_8_R1_Actionbar;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R1.v1_8_R1_GameprofileFiller;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R1.v1_8_R1_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R2.v1_8_R2_Actionbar;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R2.v1_8_R2_GameprofileFiller;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R2.v1_8_R2_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R3.v1_8_R3_Actionbar;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R3.v1_8_R3_GameprofileFiller;
import fr.antoinerochas.nickreloaded.api.nms.v1_8_R3.v1_8_R3_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.api.nms.v1_9_R1.v1_9_R1_Actionbar;
import fr.antoinerochas.nickreloaded.api.nms.v1_9_R1.v1_9_R1_GameprofileFiller;
import fr.antoinerochas.nickreloaded.api.nms.v1_9_R1.v1_9_R1_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.api.nms.v1_9_R2.v1_9_R2_Actionbar;
import fr.antoinerochas.nickreloaded.api.nms.v1_9_R2.v1_9_R2_GameprofileFiller;
import fr.antoinerochas.nickreloaded.api.nms.v1_9_R2.v1_9_R2_PlayerIdentityManager;
import org.bukkit.Bukkit;

public class NMSManager
{
    private NMSVersion MINECRAFT_SERVER_VERSION_CONSTANT = null;
    private AbstractGameprofileFiller MINECRAFT_GAMEPROFILE_FILLER = null;
    private AbstractPlayerIdentityManager MINECRAFT_PLAYER_IDENTITY_MANAGER = null;
    private AbstractActionbar MINECRAFT_ACTION_BAR = null;

    public static NMSManager getInstance()
    {
        return new NMSManager();
    }

    public void init()
    {
        MINECRAFT_SERVER_VERSION_CONSTANT = getNMSVersion();
        initModule(NMSModule.ACTIONBAR, NMSModule.GAMEPROFILE_FILLER, NMSModule.PLAYER_IDENTITY_MANAGER);
    }

    public NMSVersion getNMSVersion()
    {
        switch (getBukkitVersion())
        {
            case "v1_8_R1":
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded v1_8_R1 (" + NMSVersion.MINECRAFT_1_8_R1.getVersionName() + ").");
                return NMSVersion.MINECRAFT_1_8_R1;
            case "v1_8_R2":
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded v1_8_R2 (" + NMSVersion.MINECRAFT_1_8_R2.getVersionName() + ").");
                return NMSVersion.MINECRAFT_1_8_R2;
            case "v1_8_R3":
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded v1_8_R3 (" + NMSVersion.MINECRAFT_1_8_R3.getVersionName() + ").");
                return NMSVersion.MINECRAFT_1_8_R3;
            case "v1_9_R1":
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded v1_9_R1 (" + NMSVersion.MINECRAFT_1_9_R1.getVersionName() + ").");
                return NMSVersion.MINECRAFT_1_9_R1;
            case "v1_9_R2":
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded v1_9_R2 (" + NMSVersion.MINECRAFT_1_9_R2.getVersionName() + ").");
                return NMSVersion.MINECRAFT_1_9_R2;
            case "v1_10_R1":
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded v1_10_R1 (" + NMSVersion.MINECRAFT_1_10_R1.getVersionName() + ").");
                return NMSVersion.MINECRAFT_1_10_R1;
            case "v1_11_R1":
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded v1_11_R1 (" + NMSVersion.MINECRAFT_1_11_R1.getVersionName() + ").");
                return NMSVersion.MINECRAFT_1_11_R1;
            case "v1_12_R1":
                NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded v1_12_R1 (" + NMSVersion.MINECRAFT_1_12_R1.getVersionName() + ").");
                return NMSVersion.MINECRAFT_1_12_R1;

            default:
                NickReloadedLogger.log(NickReloadedLogger.Level.WARN, "§cServer version unknown ! (version=" + getBukkitVersion() + ")");
                return NMSVersion.UNSUPPORTED;
        }
    }

    public void initModule(NMSModule... modules)
    {
        for(NMSModule module : modules)
        {

            NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loading module §b" + module.getName() + "§6...");
            switch (module)
            {
                case GAMEPROFILE_FILLER:
                    switch (MINECRAFT_SERVER_VERSION_CONSTANT)
                    {
                        case MINECRAFT_1_12_R1:
                            MINECRAFT_GAMEPROFILE_FILLER = new v1_12_R1_GameprofileFiller();
                            break;
                        case MINECRAFT_1_11_R1:
                            MINECRAFT_GAMEPROFILE_FILLER = new v1_11_R1_GameprofileFiller();
                            break;

                        case MINECRAFT_1_9_R2:
                            MINECRAFT_GAMEPROFILE_FILLER = new v1_9_R2_GameprofileFiller();
                            break;

                        case MINECRAFT_1_9_R1:
                            MINECRAFT_GAMEPROFILE_FILLER = new v1_9_R1_GameprofileFiller();
                            break;

                        case MINECRAFT_1_8_R3:
                            MINECRAFT_GAMEPROFILE_FILLER = new v1_8_R3_GameprofileFiller();
                            break;

                        case MINECRAFT_1_8_R2:
                            MINECRAFT_GAMEPROFILE_FILLER = new v1_8_R2_GameprofileFiller();
                            break;
                        case MINECRAFT_1_8_R1:
                            MINECRAFT_GAMEPROFILE_FILLER = new v1_8_R1_GameprofileFiller();
                            break;

                        default:
                            break;
                    }
                    break;

                case PLAYER_IDENTITY_MANAGER:
                    switch (MINECRAFT_SERVER_VERSION_CONSTANT)
                    {
                        case MINECRAFT_1_12_R1:
                            MINECRAFT_PLAYER_IDENTITY_MANAGER = new v1_12_R1_PlayerIdentityManager();
                            break;
                        case MINECRAFT_1_11_R1:
                            MINECRAFT_PLAYER_IDENTITY_MANAGER = new v1_11_R1_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_9_R2:
                            MINECRAFT_PLAYER_IDENTITY_MANAGER = new v1_9_R2_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_9_R1:
                            MINECRAFT_PLAYER_IDENTITY_MANAGER = new v1_9_R1_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_8_R3:
                            MINECRAFT_PLAYER_IDENTITY_MANAGER = new v1_8_R3_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_8_R2:
                            MINECRAFT_PLAYER_IDENTITY_MANAGER = new v1_8_R2_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_8_R1:
                            MINECRAFT_PLAYER_IDENTITY_MANAGER = new v1_8_R1_PlayerIdentityManager();
                            break;

                        default:
                            break;
                    }
                    break;

                case ACTIONBAR:
                    switch (MINECRAFT_SERVER_VERSION_CONSTANT)
                    {
                        case MINECRAFT_1_12_R1:
                            MINECRAFT_ACTION_BAR = new v1_12_R1_Actionbar();
                            break;
                        case MINECRAFT_1_11_R1:
                            MINECRAFT_ACTION_BAR = new v1_11_R1_Actionbar();
                            break;

                        case MINECRAFT_1_9_R2:
                            MINECRAFT_ACTION_BAR = new v1_9_R2_Actionbar();
                            break;

                        case MINECRAFT_1_9_R1:
                            MINECRAFT_ACTION_BAR = new v1_9_R1_Actionbar();
                            break;

                        case MINECRAFT_1_8_R3:
                            MINECRAFT_ACTION_BAR = new v1_8_R3_Actionbar();
                            break;

                        case MINECRAFT_1_8_R2:
                            MINECRAFT_ACTION_BAR = new v1_8_R2_Actionbar();
                            break;

                        case MINECRAFT_1_8_R1:
                            MINECRAFT_ACTION_BAR = new v1_8_R1_Actionbar();
                            break;

                        default:
                            break;
                    }

                    break;

                default:
                    break;
            }

            NickReloadedLogger.log(NickReloadedLogger.Level.INFO, "§6Loaded §b" + module.getName() + " §6for version(s) §a" + MINECRAFT_SERVER_VERSION_CONSTANT.getVersionName());
        }
    }

    public String getBukkitVersion()
    {
        final String packageName = Bukkit.getServer().getClass().getPackage().getName();

        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public enum NMSVersion
    {
        UNSUPPORTED("Unsupported"),
        MINECRAFT_1_8_R1("1.8-1.8.2"),
        MINECRAFT_1_8_R2("1.8.3-1.8.7"),
        MINECRAFT_1_8_R3("1.8.8"),
        MINECRAFT_1_9_R1("1.9-1.9.3"),
        MINECRAFT_1_9_R2("1.9.4"),
        MINECRAFT_1_10_R1("1.10-1.10.2"),
        MINECRAFT_1_11_R1("1.11-1.11.2"),
        MINECRAFT_1_12_R1("1.12-1.12.1");

        private String version;

        NMSVersion(String version)
        {
            this.version = version;
        }

        public String getVersionName()
        {
            return version;
        }

    }

    public AbstractGameprofileFiller getGameprofileFiller()
    {
        return MINECRAFT_GAMEPROFILE_FILLER;
    }

    public AbstractPlayerIdentityManager getIdentityManager()
    {
        return MINECRAFT_PLAYER_IDENTITY_MANAGER;
    }

    public AbstractActionbar getActionbar()
    {
        return MINECRAFT_ACTION_BAR;
    }

    public enum NMSModule
    {
        GAMEPROFILE_FILLER("Gameprofile Filler Module"),
        PLAYER_IDENTITY_MANAGER("Player Identity Module"),
        ACTIONBAR("ActionBar Module");

        private String name;

        NMSModule(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }
    }
}
