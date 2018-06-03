package io.idden.nickreloaded.core.nms.v1_12_R1;

import io.idden.nickreloaded.core.nms.impl.IActionbar;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_12_R1_Actionbar
        implements IActionbar
{
    @Override
    public void sendActionbar(Player player, String message)
    {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }
}
