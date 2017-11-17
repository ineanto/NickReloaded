package fr.antoinerochas.nickreloaded.api.manager;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.api.config.LanguageFileValues;
import fr.antoinerochas.nickreloaded.api.storage.AccountProvider;
import fr.antoinerochas.nickreloaded.api.storage.NickData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class NickManager
{
    private ArrayList<Player> players = new ArrayList<>();
    private HashMap<Player, BukkitTask> tasks = new HashMap<>();
    private BukkitTask task;

    public void nick(Player player, String nick, String skin)
    {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(NickReloaded.getInstance(),
                                                                () ->
                                                                {
                                                                    if (tasks.containsKey(player))
                                                                    {
                                                                        new NMSManager().getActionbar().sendActionbar(player, StorageManager.getInstance().getLangFile().getString(LanguageFileValues.MESSAGES_COMMANDS_NICK_ACTIVE.getValue()));
                                                                    }
                                                                },
                                                                0,
                                                                20);

        tasks.putIfAbsent(player,
                          task);

        NickData nickData = new AccountProvider(player.getUniqueId()).getData();

        Bukkit.getScheduler().runTaskAsynchronously(NickReloaded.getInstance(),
                                                    () ->
                                                    {
                                                        if (Objects.equals(nick,
                                                                           "") || nick == null || Objects.equals(skin,
                                                                                                                 "") || skin == null)
                                                        {
                                                            if (players.contains(player))
                                                            {
                                                                player.setDisplayName(player.getName());
                                                                player.setPlayerListName(player.getName());

                                                                new NMSManager().getIdentityManager().setPlayerName(player, player.getName());
                                                                new NMSManager().getIdentityManager().setPlayerSkin(player, player.getName());

                                                                stopTask(player);

                                                                nickData.setName(null);
                                                                nickData.setSkin(null);
                                                                nickData.setNicked(false);

                                                                players.remove(player);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if (! players.contains(player))
                                                            {
                                                                player.setDisplayName(nick);
                                                                player.setPlayerListName(nick);

                                                                new NMSManager().getIdentityManager().setPlayerName(player, nick);
                                                                new NMSManager().getIdentityManager().setPlayerSkin(player, skin);

                                                                //IF DATA NULL LOAD DATA

                                                                nickData.setName(nick);
                                                                nickData.setSkin(skin);
                                                                nickData.setNicked(true);

                                                                players.add(player);
                                                            }
                                                        }
                                                    });
    }

    private void stopTask(Player player)
    {
        if (tasks.containsKey(player))
        {
            tasks.get(player).cancel();
            tasks.remove(player);
        }
    }

    public void processData(DataStatus status)
    {
        if (status == DataStatus.DISABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player -> new AccountProvider(player.getUniqueId()).sendDataToStorage(new AccountProvider(player.getUniqueId()).getData()));
        }
        else if (status == DataStatus.ENABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player ->
                                              {
                                                  new AccountProvider(player.getUniqueId()).getData();
                                              });
        }
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public enum DataStatus
    {
        ENABLING,
        DISABLING;
    }
}
