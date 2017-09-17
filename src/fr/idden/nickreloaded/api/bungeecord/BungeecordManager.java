package fr.idden.nickreloaded.api.bungeecord;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.idden.nickreloaded.Messages;
import fr.idden.nickreloaded.NickReloaded;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeecordManager
        implements PluginMessageListener
{
    public BungeecordManager(JavaPlugin javaPlugin)
    {
        if (isBungeecordEnabled())
        {
            NickReloaded.log(Messages.BUNGEECORD_FOUND.m());

            //Enable Bungeecord
            javaPlugin.getServer().getMessenger().registerOutgoingPluginChannel(javaPlugin,
                                                                                "NR|O");
            javaPlugin.getServer().getMessenger().registerIncomingPluginChannel(javaPlugin,
                                                                                "NR|I",
                                                                                this);
        }
    }

    public static boolean isBungeecordEnabled()
    {
        return true;
    }

    @Override
    public synchronized void onPluginMessageReceived(String channel, Player player, byte[] message)
    {
        if (! channel.equals("NR|I"))
        {
            return;
        }

        NickReloaded.log("MESSAGE RECIVED FROM NR|I CHANNEL !");

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();

        System.out.println(in.toString());
        System.out.println(in.readUTF());
    }
}
