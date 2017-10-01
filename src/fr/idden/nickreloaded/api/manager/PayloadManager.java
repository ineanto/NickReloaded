package fr.idden.nickreloaded.api.manager;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.nms.impl.AbstractActionbar;
import fr.idden.nickreloaded.api.nms.impl.AbstractGameprofileFiller;
import fr.idden.nickreloaded.api.nms.impl.AbstractPlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.throwable.PayloadUnsupportedVersionException;
import fr.idden.nickreloaded.api.nms.v1_11_R1.v1_11_R1_Actionbar;
import fr.idden.nickreloaded.api.nms.v1_11_R1.v1_11_R1_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v1_11_R1.v1_11_R1_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v1_12_R1.v1_12_R1_Actionbar;
import fr.idden.nickreloaded.api.nms.v1_12_R1.v1_12_R1_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v1_12_R1.v1_12_R1_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v1_8_R1.v1_8_R1_Actionbar;
import fr.idden.nickreloaded.api.nms.v1_8_R1.v1_8_R1_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v1_8_R1.v1_8_R1_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v1_8_R2.v1_8_R2_Actionbar;
import fr.idden.nickreloaded.api.nms.v1_8_R2.v1_8_R2_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v1_8_R2.v1_8_R2_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v1_8_R3.v1_8_R3_Actionbar;
import fr.idden.nickreloaded.api.nms.v1_8_R3.v1_8_R3_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v1_8_R3.v1_8_R3_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v1_9_R1.v1_9_R1_Actionbar;
import fr.idden.nickreloaded.api.nms.v1_9_R1.v1_9_R1_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v1_9_R1.v1_9_R1_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v1_9_R2.v1_9_R2_Actionbar;
import fr.idden.nickreloaded.api.nms.v1_9_R2.v1_9_R2_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v1_9_R2.v1_9_R2_PlayerIdentityManager;
import org.bukkit.Bukkit;

public class PayloadManager
{
    private NMSVersion MINECRAFT_SERVER_VERSION_CONSTANT = null;
    private AbstractGameprofileFiller MINECRAFT_GAMEPROFILE_FILLER = null;
    private AbstractPlayerIdentityManager MINECRAFT_PLAYER_IDENTITY_MANAGER = null;
    private AbstractActionbar MINECRAFT_ACTION_BAR = null;

    public void init()
            throws PayloadUnsupportedVersionException
    {
        MINECRAFT_SERVER_VERSION_CONSTANT = getNMSVersion();
        initModule(NMSModule.ACTIONBAR, NMSModule.GAMEPROFILE_FILLER, NMSModule.PLAYER_IDENTITY_MANAGER);
    }

    public NMSVersion getNMSVersion()
    {
        switch (getBukkitVersion())
        {
            case "v1_8_R1":
                NickReloaded.getInstance().log("§6Loaded v1_8_R1 (" + NMSVersion.MINECRAFT_1_8_R1.getVersionName() + ") payload.");
                return NMSVersion.MINECRAFT_1_8_R1;
            case "v1_8_R2":
                NickReloaded.getInstance().log("§6Loaded v1_8_R2 (" + NMSVersion.MINECRAFT_1_8_R2.getVersionName() + ") payload.");
                return NMSVersion.MINECRAFT_1_8_R2;
            case "v1_8_R3":
                NickReloaded.getInstance().log("§6Loaded v1_8_R3 (" + NMSVersion.MINECRAFT_1_8_R3.getVersionName() + ") payload.");
                return NMSVersion.MINECRAFT_1_8_R3;
            case "v1_9_R1":
                NickReloaded.getInstance().log("§6Loaded v1_9_R1 (" + NMSVersion.MINECRAFT_1_9_R1.getVersionName() + ") payload.");
                return NMSVersion.MINECRAFT_1_9_R1;
            case "v1_9_R2":
                NickReloaded.getInstance().log("§6Loaded v1_9_R2 (" + NMSVersion.MINECRAFT_1_9_R2.getVersionName() + ") payload.");
                return NMSVersion.MINECRAFT_1_9_R2;
            case "v1_10_R1":
                NickReloaded.getInstance().log("§6Loaded v1_10_R1 (" + NMSVersion.MINECRAFT_1_10_R1.getVersionName() + ") payload.");
                return NMSVersion.MINECRAFT_1_10_R1;
            case "v1_11_R1":
                NickReloaded.getInstance().log("§6Loaded v1_11_R1 (" + NMSVersion.MINECRAFT_1_11_R1.getVersionName() + ") payload.");
                return NMSVersion.MINECRAFT_1_11_R1;
            case "v1_12_R1":
                NickReloaded.getInstance().log("§6Loaded v1_12_R1 (" + NMSVersion.MINECRAFT_1_12_R1.getVersionName() + ") payload.");
                return NMSVersion.MINECRAFT_1_12_R1;

            default:
                NickReloaded.getInstance().log("§cServer version unknown ! (version=" + getBukkitVersion() + ")");
                return NMSVersion.UNSUPPORTED;
        }
    }

    public void initModule(NMSModule... modules)
            throws PayloadUnsupportedVersionException
    {
        for(NMSModule module : modules)
        {

            NickReloaded.getInstance().log("§6Loading module §b" + module.getName() + "§6...");
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
                            throw new PayloadUnsupportedVersionException(module);
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
                            throw new PayloadUnsupportedVersionException(module);
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
                            throw new PayloadUnsupportedVersionException(module);
                    }

                    break;

                default:
                    break;
            }

            logInitModule(module);
        }
    }

    public String getBukkitVersion()
    {
        final String packageName = Bukkit.getServer().getClass().getPackage().getName();

        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public void logInitModule(NMSModule module)
    {
        NickReloaded.getInstance().log("§aLoaded §b" + module.name() + " §afor version(s) §b" + MINECRAFT_SERVER_VERSION_CONSTANT.getVersionName());
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
        GAMEPROFILE_FILLER("Gameprofile Filler (GF) "),
        PLAYER_IDENTITY_MANAGER("Player Identity Manager (PIM) "),
        ACTIONBAR("Actionbar");

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
