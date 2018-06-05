/*
 * MIT License
 *
 * Copyright (c) 2017 Antoine "Idden" ROCHAS
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

package io.idden.nickreloaded.nms.v1_11_R1;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_11_R1.MinecraftServer;

public class v1_11_R1_GameprofileFiller
        implements IGameProfileFiller
{
    @Override
    public GameProfile fillGameprofile(GameProfile gameProfile)
    {
        try
        {
            if (gameProfile != null)
            {
                GameProfile gameProfile1 = null;
                if (gameProfile.getName() != null)
                {
                    gameProfile1 = MinecraftServer.getServer().getUserCache().getProfile(gameProfile.getName());
                }
                if (gameProfile1 == null)
                {
                    gameProfile1 = MinecraftServer.getServer().getUserCache().a(gameProfile.getId());
                }
                if (gameProfile1 == null)
                {
                    gameProfile1 = gameProfile;
                }
                if (Iterables.getFirst(gameProfile1.getProperties().get("textures"),
                                       null) == null)
                {
                    gameProfile1 = MinecraftServer.getServer().az().fillProfileProperties(gameProfile1,
                                                                                          true);
                }
                return gameProfile1;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
