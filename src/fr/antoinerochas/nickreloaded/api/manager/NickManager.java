package fr.antoinerochas.nickreloaded.api.manager;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.api.storage.AccountProvider;
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
                                                                            //SEND ACTIONBAR (IF NICKED)
                                                                    }
                                                                },
                                                                0,
                                                                20);

        tasks.putIfAbsent(player,
                          task);


        Bukkit.getScheduler().runTaskAsynchronously(NickReloaded.getInstance(),
                                                    () ->
                                                    {
                                                        if (Objects.equals(nick,
                                                                           "") || nick == null || Objects.equals(skin,
                                                                                                                 "") || skin == null)
                                                        {
                                                            players.remove(player);

                                                            player.setDisplayName(player.getName());
                                                            player.setPlayerListName(player.getName());

                                                            //SET PLAYER SKIN AND NAME

                                                            stopTask(player);

                                                            //SET STORAGE TO NULL
                                                        }
                                                        else
                                                        {
                                                            if (! players.contains(player))
                                                            {
                                                                players.add(player);
                                                            }

                                                            player.setDisplayName(nick);
                                                            player.setPlayerListName(nick);

                                                            //SET PLAYER SKIN AND NAME

                                                            //IF DATA NULL LOAD DATA

                                                            //DEFINE DATA
                                                        }
                                                    });
    }

    public void stopTask(Player player)
    {
        if (tasks.containsKey(player))
        {
            tasks.get(player).cancel();
            tasks.remove(player);
        }
    }

    public boolean isNicked(Player player)
    {
        return new AccountProvider(player.getUniqueId()).getAccount().isNicked();
    }



    public void processData(DataStatus status)
    {
        if (status == DataStatus.DISABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player ->
                                              {
                                                  //SAVE DATA (IF NICKED)
                                              });
        }
        else if (status == DataStatus.ENABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player ->
                                              {
                                                  //LOAD DATA (IF NICKED)
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
