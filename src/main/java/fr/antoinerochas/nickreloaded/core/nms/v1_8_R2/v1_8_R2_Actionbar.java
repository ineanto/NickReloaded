package fr.antoinerochas.nickreloaded.core.nms.v1_8_R2;

import fr.antoinerochas.nickreloaded.core.nms.impl.AbstractActionbar;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_8_R2_Actionbar
        implements AbstractActionbar
{
    @Override
    public void sendActionbar(Player player, String message)
    {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }
}
