/*
 * MIT License
 *
 * Copyright (c) 2018 Wesley Smith
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.wesjd.anvilgui.version;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Wraps versions to be able to easily use different NMS server versions
 * @author Wesley Smith
 * @since 1.0
 */
public interface VersionWrapper {

    /**
     * Gets the next available NMS container id for the player
     * @param player The player to get the next container id of
     * @return The next available NMS container id0
     */
    int getNextContainerId(Player player);

    /**
     * Closes the current inventory for the player
     * @param player The player that needs their current inventory closed
     */
    void handleInventoryCloseEvent(Player player);

    /**
     * Sends PacketPlayOutOpenWindow to the player with the container id
     * @param player The player to send the packet to
     * @param containerId The container id to open
     */
    void sendPacketOpenWindow(Player player, int containerId);

    /**
     * Sends PacketPlayOutCloseWindow to the player with the contaienr id
     * @param player The player to send the packet to
     * @param containerId The container id to close
     */
    void sendPacketCloseWindow(Player player, int containerId);

    /**
     * Sets the NMS player's active container to the default one
     * @param player The player to set the active container of
     */
    void setActiveContainerDefault(Player player);

    /**
     * Sets the NMS player's active container to the one supplied
     * @param player The player to set the active container of
     * @param container The container to set as active
     */
    void setActiveContainer(Player player, Object container);

    /**
     * Sets the supplied windowId of the supplied Container
     * @param container The container to set the windowId of
     * @param containerId The new windowId
     */
    void setActiveContainerId(Object container, int containerId);

    /**
     * Adds a slot listener to the supplied container for the player
     * @param container The container to add the slot listener to
     * @param player The player to have as a listener
     */
    void addActiveContainerSlotListener(Object container, Player player);

    /**
     * Gets the {@link Inventory} wrapper of the supplied NMS container
     * @param container The NMS container to get the {@link Inventory} of
     * @return The inventory of the NMS container
     */
    Inventory toBukkitInventory(Object container);

    /**
     * Creates a new ContainerAnvil
     * @param player The player to get the container of
     * @return The Container instance
     */
    Object newContainerAnvil(Player player);

}
