package fr.idden.nickreloaded.api.nms;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.nms.impl.AbstractActionbar;
import fr.idden.nickreloaded.api.nms.impl.AbstractGameprofileFiller;
import fr.idden.nickreloaded.api.nms.impl.AbstractPlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.throwable.PayloadModuleUnsupportedVersionException;
import fr.idden.nickreloaded.api.nms.v1_12_R1.v_1_12_R1_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v1_12_R1.v_1_12_R1_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v1_12_R1.v_1_12_R1_Actionbar;
import fr.idden.nickreloaded.api.nms.v_1_11_R1.v_1_11_R1_Actionbar;
import fr.idden.nickreloaded.api.nms.v_1_11_R1.v_1_11_R1_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v_1_11_R1.v_1_11_R1_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v_1_8_R1.v_1_8_R1_Actionbar;
import fr.idden.nickreloaded.api.nms.v_1_8_R1.v_1_8_R1_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v_1_8_R1.v_1_8_R1_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v_1_8_R2.v_1_8_R2_Actionbar;
import fr.idden.nickreloaded.api.nms.v_1_8_R2.v_1_8_R2_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v_1_8_R2.v_1_8_R2_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v_1_8_R3.v_1_8_R3_Actionbar;
import fr.idden.nickreloaded.api.nms.v_1_8_R3.v_1_8_R3_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v_1_8_R3.v_1_8_R3_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v_1_9_R1.v_1_9_R1_Actionbar;
import fr.idden.nickreloaded.api.nms.v_1_9_R1.v_1_9_R1_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v_1_9_R1.v_1_9_R1_PlayerIdentityManager;
import fr.idden.nickreloaded.api.nms.v_1_9_R2.v_1_9_R2_Actionbar;
import fr.idden.nickreloaded.api.nms.v_1_9_R2.v_1_9_R2_GameprofileFiller;
import fr.idden.nickreloaded.api.nms.v_1_9_R2.v_1_9_R2_PlayerIdentityManager;
import org.bukkit.Bukkit;

public class PayloadManager
{
    private static NMSVersion MINECRAFT_SERVER_VERSION_CONSTANT = null;
    private static AbstractGameprofileFiller MINECRAFT_GAMEPROFILE_FILLER = null;
    private static AbstractPlayerIdentityManager MINECRAFT_PLAYER_IDENTITY_MANAGER = null;
    private static AbstractActionbar MINECRAFT_ACTION_BAR = null;

    public PayloadManager()
            throws PayloadModuleUnsupportedVersionException
    {
        MINECRAFT_SERVER_VERSION_CONSTANT = v();
        initModule(NMSModule.ACTIONBAR);
        initModule(NMSModule.GAMEPROFILE_FILLER);
        initModule(NMSModule.PLAYER_IDENTITY_MANAGER);
    }

    public static NMSVersion getVersion()
    {
        return MINECRAFT_SERVER_VERSION_CONSTANT;
    }

    public static AbstractGameprofileFiller getGameprofileFiller()
    {
        return MINECRAFT_GAMEPROFILE_FILLER;
    }

    public static AbstractPlayerIdentityManager getIdentityManager()
    {
        return MINECRAFT_PLAYER_IDENTITY_MANAGER;
    }

    public static AbstractActionbar getActionbar()
    {
        return MINECRAFT_ACTION_BAR;
    }

    private static NMSVersion v()
    {
        switch (vD())
        {
            case "v1_8_R1":
                NickReloaded.log("§6Loaded v1_8_R1 payload.");
                return NMSVersion.MINECRAFT_1_8_R1;
            case "v1_8_R2":
                NickReloaded.log("§6Loaded v1_8_R2 payload.");
                return NMSVersion.MINECRAFT_1_8_R2;
            case "v_1_8_R3":
                NickReloaded.log("§6Loaded v_1_8_R3 payload.");
                return NMSVersion.MINECRAFT_1_8_R3;
            case "v1_9_R1":
                NickReloaded.log("§6Loaded v1_9_R1 payload.");
                return NMSVersion.MINECRAFT_1_9_R1;
            case "v1_9_R2":
                NickReloaded.log("§6Loaded v1_9_R2 payload.");
                return NMSVersion.MINECRAFT_1_9_R2;
            case "v1_10_R1":
                NickReloaded.log("§6Loaded v1_10_R1 payload.");
                return NMSVersion.MINECRAFT_1_10_R1;
            case "v1_11_R1":
                NickReloaded.log("§6Loaded v1_11_R1 payload.");
                return NMSVersion.MINECRAFT_1_11_R1;
            case "v1_12_R1":
                NickReloaded.log("§6Loaded v1_12_R1 payload.");
                return NMSVersion.MINECRAFT_1_12_R1;

            default:
                NickReloaded.log("§cUnsupported plugin version.");
                return NMSVersion.UNSUPPORTED;
        }
    }

    private static void initModule(NMSModule module)
            throws PayloadModuleUnsupportedVersionException
    {
        NickReloaded.log("§cLoading module §b" + module.name() + "§c...");
        switch (module)
        {
            case GAMEPROFILE_FILLER:
                switch (MINECRAFT_SERVER_VERSION_CONSTANT)
                {
                    case MINECRAFT_1_12_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + NMSVersion.MINECRAFT_1_12_R1.v());
                        MINECRAFT_GAMEPROFILE_FILLER = new v_1_12_R1_GameprofileFiller();
                        break;
                    case MINECRAFT_1_11_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + NMSVersion.MINECRAFT_1_11_R1.v());
                        MINECRAFT_GAMEPROFILE_FILLER = new v_1_11_R1_GameprofileFiller();
                        break;

                    case MINECRAFT_1_9_R2:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + NMSVersion.MINECRAFT_1_9_R2.v());
                        MINECRAFT_GAMEPROFILE_FILLER = new v_1_9_R2_GameprofileFiller();
                        break;

                    case MINECRAFT_1_9_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + NMSVersion.MINECRAFT_1_9_R1.v());
                        MINECRAFT_GAMEPROFILE_FILLER = new v_1_9_R1_GameprofileFiller();
                        break;

                    case MINECRAFT_1_8_R3:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + NMSVersion.MINECRAFT_1_8_R3.v());
                        MINECRAFT_GAMEPROFILE_FILLER = new v_1_8_R3_GameprofileFiller();
                        break;

                    case MINECRAFT_1_8_R2:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_GAMEPROFILE_FILLER = new v_1_8_R2_GameprofileFiller();
                        break;

                    case MINECRAFT_1_8_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_GAMEPROFILE_FILLER = new v_1_8_R1_GameprofileFiller();
                        break;

                    default:
                        throw new PayloadModuleUnsupportedVersionException("Module unsupported for this version",
                                                                           module);
                }
                break;

            case PLAYER_IDENTITY_MANAGER:
                switch (MINECRAFT_SERVER_VERSION_CONSTANT)
                {
                    case MINECRAFT_1_12_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_PLAYER_IDENTITY_MANAGER = new v_1_12_R1_PlayerIdentityManager();
                        break;
                    case MINECRAFT_1_11_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_PLAYER_IDENTITY_MANAGER = new v_1_11_R1_PlayerIdentityManager();
                        break;

                    case MINECRAFT_1_9_R2:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_PLAYER_IDENTITY_MANAGER = new v_1_9_R2_PlayerIdentityManager();
                        break;

                    case MINECRAFT_1_9_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_PLAYER_IDENTITY_MANAGER = new v_1_9_R1_PlayerIdentityManager();
                        break;

                    case MINECRAFT_1_8_R3:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_PLAYER_IDENTITY_MANAGER = new v_1_8_R3_PlayerIdentityManager();
                        break;

                    case MINECRAFT_1_8_R2:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_PLAYER_IDENTITY_MANAGER = new v_1_8_R2_PlayerIdentityManager();
                        break;

                    case MINECRAFT_1_8_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_PLAYER_IDENTITY_MANAGER = new v_1_8_R1_PlayerIdentityManager();
                        break;

                    default:
                        throw new PayloadModuleUnsupportedVersionException("Module unsupported for this version",
                                                                           module);
                }
                break;

            case ACTIONBAR:
                switch (MINECRAFT_SERVER_VERSION_CONSTANT)
                {
                    case MINECRAFT_1_12_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_ACTION_BAR = new v_1_12_R1_Actionbar();
                        break;
                    case MINECRAFT_1_11_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_ACTION_BAR = new v_1_11_R1_Actionbar();
                        break;

                    case MINECRAFT_1_9_R2:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_ACTION_BAR = new v_1_9_R2_Actionbar();
                        break;

                    case MINECRAFT_1_9_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_ACTION_BAR = new v_1_9_R1_Actionbar();
                        break;

                    case MINECRAFT_1_8_R3:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_ACTION_BAR = new v_1_8_R3_Actionbar();
                        break;

                    case MINECRAFT_1_8_R2:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_ACTION_BAR = new v_1_8_R2_Actionbar();
                        break;

                    case MINECRAFT_1_8_R1:
                        NickReloaded.log("§cLoaded §b" + module.name() + " §cfor version(s) §b" + getVersion().v());
                        MINECRAFT_ACTION_BAR = new v_1_8_R1_Actionbar();
                        break;

                    default:
                        throw new PayloadModuleUnsupportedVersionException("Module unsupported for this version",
                                                                           module);
                }
                break;

            default:
                break;
        }
    }

    public static String vD()
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

        private String v;

        NMSVersion(String v)
        {
            this.v = v;
        }

        public String v()
        {
            return v;
        }

        public boolean isSupported()
        {
            return MINECRAFT_SERVER_VERSION_CONSTANT == MINECRAFT_1_12_R1 || MINECRAFT_SERVER_VERSION_CONSTANT == MINECRAFT_1_11_R1;
        }
    }

    public enum NMSModule
    {
        GAMEPROFILE_FILLER,
        PLAYER_IDENTITY_MANAGER,
        ACTIONBAR,
        UNSUPPORTED
    }
}
