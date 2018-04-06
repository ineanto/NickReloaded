package fr.antoinerochas.nickreloaded.core.manager;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.core.error.NRErrorHandler;
import fr.antoinerochas.nickreloaded.core.logger.NRLPrefix;
import fr.antoinerochas.nickreloaded.core.nms.impl.AbstractActionbar;
import fr.antoinerochas.nickreloaded.core.nms.impl.AbstractGameprofileFiller;
import fr.antoinerochas.nickreloaded.core.nms.impl.AbstractPlayerIdentityManager;
import fr.antoinerochas.nickreloaded.core.nms.v1_11_R1.v1_11_R1_Actionbar;
import fr.antoinerochas.nickreloaded.core.nms.v1_11_R1.v1_11_R1_GameprofileFiller;
import fr.antoinerochas.nickreloaded.core.nms.v1_11_R1.v1_11_R1_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.core.nms.v1_12_R1.v1_12_R1_Actionbar;
import fr.antoinerochas.nickreloaded.core.nms.v1_12_R1.v1_12_R1_GameprofileFiller;
import fr.antoinerochas.nickreloaded.core.nms.v1_12_R1.v1_12_R1_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R1.v1_8_R1_Actionbar;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R1.v1_8_R1_GameprofileFiller;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R1.v1_8_R1_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R2.v1_8_R2_Actionbar;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R2.v1_8_R2_GameprofileFiller;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R2.v1_8_R2_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R3.v1_8_R3_Actionbar;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R3.v1_8_R3_GameprofileFiller;
import fr.antoinerochas.nickreloaded.core.nms.v1_8_R3.v1_8_R3_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.core.nms.v1_9_R1.v1_9_R1_Actionbar;
import fr.antoinerochas.nickreloaded.core.nms.v1_9_R1.v1_9_R1_GameprofileFiller;
import fr.antoinerochas.nickreloaded.core.nms.v1_9_R1.v1_9_R1_PlayerIdentityManager;
import fr.antoinerochas.nickreloaded.core.nms.v1_9_R2.v1_9_R2_Actionbar;
import fr.antoinerochas.nickreloaded.core.nms.v1_9_R2.v1_9_R2_GameprofileFiller;
import fr.antoinerochas.nickreloaded.core.nms.v1_9_R2.v1_9_R2_PlayerIdentityManager;
import org.bukkit.Bukkit;

/**
 * Class used to load all the wrappers used to permit the use of multiple versions.
 *
 * @author Antoine "Idden" R.
 * @since 2.0-rc1
 */
public class NRWrapperLoader
{
    private NMSVersion MC_SERVER_VER = null;
    private AbstractGameprofileFiller MC_GAMEPROFILE_WRAPPER = null;
    private AbstractPlayerIdentityManager MC_PLAYERIDENTITY_WRAPPER = null;
    private AbstractActionbar MC_ACTIONBAR_WRAPPER = null;

    public static NRWrapperLoader get()
    {
        return new NRWrapperLoader();
    }

    public void init()
    {
        MC_SERVER_VER = getNMSVersion();
        initWrapper(NMSModule.ACTIONBAR, NMSModule.GAMEPROFILE_FILLER, NMSModule.PLAYER_IDENTITY_MANAGER);
    }

    public NMSVersion getNMSVersion()
    {
        NMSVersion temp = null;

        switch (getBukkitVersion())
        {
            case "v1_8_R1":
                temp = NMSVersion.MINECRAFT_1_8_R1;
                break;
            case "v1_8_R2":
                temp = NMSVersion.MINECRAFT_1_8_R2;
                break;
            case "v1_8_R3":
                temp = NMSVersion.MINECRAFT_1_8_R3;
                break;
            case "v1_9_R1":
                temp = NMSVersion.MINECRAFT_1_9_R1;
                break;
            case "v1_9_R2":
                temp = NMSVersion.MINECRAFT_1_9_R2;
                break;
            case "v1_10_R1":
                temp = NMSVersion.MINECRAFT_1_10_R1;
                break;
            case "v1_11_R1":
                temp = NMSVersion.MINECRAFT_1_11_R1;
                break;
            case "v1_12_R1":
                temp = NMSVersion.MINECRAFT_1_12_R1;
                break;

            default:
                NRErrorHandler.get().handleError("§cServer version unknown ! (version=" + getBukkitVersion() + ")");
                temp = NMSVersion.UNSUPPORTED;
                break;
        }

        if(temp != NMSVersion.UNSUPPORTED)
        {
            NickReloaded.getNRLogger().log(NRLPrefix.INFO, "§6Loaded wrappers for version: " + temp.getVersionName() + ".");
            return temp;
        }

        //todo: clean this
        NRErrorHandler.get().handleError("The server version is not supported, consider updating your server. Unloading...");
        Bukkit.getPluginManager().disablePlugin(NickReloaded.getInstance());
        return null;
    }

    public void initWrapper(NMSModule... modules)
    {
        for(NMSModule module : modules)
        {

            NickReloaded.getNRLogger().log(NRLPrefix.INFO, "§6Loading module §b" + module.getName() + "§6...");
            switch (module)
            {
                case GAMEPROFILE_FILLER:
                    switch (MC_SERVER_VER)
                    {
                        case MINECRAFT_1_12_R1:
                            MC_GAMEPROFILE_WRAPPER = new v1_12_R1_GameprofileFiller();
                            break;
                        case MINECRAFT_1_11_R1:
                            MC_GAMEPROFILE_WRAPPER = new v1_11_R1_GameprofileFiller();
                            break;

                        case MINECRAFT_1_9_R2:
                            MC_GAMEPROFILE_WRAPPER = new v1_9_R2_GameprofileFiller();
                            break;

                        case MINECRAFT_1_9_R1:
                            MC_GAMEPROFILE_WRAPPER = new v1_9_R1_GameprofileFiller();
                            break;

                        case MINECRAFT_1_8_R3:
                            MC_GAMEPROFILE_WRAPPER = new v1_8_R3_GameprofileFiller();
                            break;

                        case MINECRAFT_1_8_R2:
                            MC_GAMEPROFILE_WRAPPER = new v1_8_R2_GameprofileFiller();
                            break;
                        case MINECRAFT_1_8_R1:
                            MC_GAMEPROFILE_WRAPPER = new v1_8_R1_GameprofileFiller();
                            break;

                        default:
                            break;
                    }
                    break;

                case PLAYER_IDENTITY_MANAGER:
                    switch (MC_SERVER_VER)
                    {
                        case MINECRAFT_1_12_R1:
                            MC_PLAYERIDENTITY_WRAPPER = new v1_12_R1_PlayerIdentityManager();
                            break;
                        case MINECRAFT_1_11_R1:
                            MC_PLAYERIDENTITY_WRAPPER = new v1_11_R1_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_9_R2:
                            MC_PLAYERIDENTITY_WRAPPER = new v1_9_R2_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_9_R1:
                            MC_PLAYERIDENTITY_WRAPPER = new v1_9_R1_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_8_R3:
                            MC_PLAYERIDENTITY_WRAPPER = new v1_8_R3_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_8_R2:
                            MC_PLAYERIDENTITY_WRAPPER = new v1_8_R2_PlayerIdentityManager();
                            break;

                        case MINECRAFT_1_8_R1:
                            MC_PLAYERIDENTITY_WRAPPER = new v1_8_R1_PlayerIdentityManager();
                            break;

                        default:
                            break;
                    }
                    break;

                case ACTIONBAR:
                    switch (MC_SERVER_VER)
                    {
                        case MINECRAFT_1_12_R1:
                            MC_ACTIONBAR_WRAPPER = new v1_12_R1_Actionbar();
                            break;
                        case MINECRAFT_1_11_R1:
                            MC_ACTIONBAR_WRAPPER = new v1_11_R1_Actionbar();
                            break;

                        case MINECRAFT_1_9_R2:
                            MC_ACTIONBAR_WRAPPER = new v1_9_R2_Actionbar();
                            break;

                        case MINECRAFT_1_9_R1:
                            MC_ACTIONBAR_WRAPPER = new v1_9_R1_Actionbar();
                            break;

                        case MINECRAFT_1_8_R3:
                            MC_ACTIONBAR_WRAPPER = new v1_8_R3_Actionbar();
                            break;

                        case MINECRAFT_1_8_R2:
                            MC_ACTIONBAR_WRAPPER = new v1_8_R2_Actionbar();
                            break;

                        case MINECRAFT_1_8_R1:
                            MC_ACTIONBAR_WRAPPER = new v1_8_R1_Actionbar();
                            break;

                        default:
                            break;
                    }

                    break;

                default:
                    break;
            }

            NickReloaded.getNRLogger().log(NRLPrefix.INFO, "§6Loaded §b" + module.getName() + " §6for version(s) §a" + MC_SERVER_VER.getVersionName());
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
        return MC_GAMEPROFILE_WRAPPER;
    }

    public AbstractPlayerIdentityManager getIdentityManager()
    {
        return MC_PLAYERIDENTITY_WRAPPER;
    }

    public AbstractActionbar getActionbar()
    {
        return MC_ACTIONBAR_WRAPPER;
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
