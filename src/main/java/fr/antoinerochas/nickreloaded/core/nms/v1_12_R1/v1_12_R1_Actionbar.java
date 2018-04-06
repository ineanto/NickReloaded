package fr.antoinerochas.nickreloaded.core.nms.v1_12_R1;

import fr.antoinerochas.nickreloaded.core.nms.impl.AbstractActionbar;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_12_R1_Actionbar
        implements AbstractActionbar
{
    @Override
    public void sendActionbar(Player player, String message)
    {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }
}
