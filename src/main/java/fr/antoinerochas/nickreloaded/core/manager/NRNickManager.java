package fr.antoinerochas.nickreloaded.core.manager;

import fr.antoinerochas.nickreloaded.NickReloaded;
import fr.antoinerochas.nickreloaded.core.config.LanguageFileValues;
import fr.antoinerochas.nickreloaded.core.storage.AccountProvider;
import fr.antoinerochas.nickreloaded.core.storage.NRData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

public class NRNickManager
{
    private ArrayList<Player> players = new ArrayList<>();

    private HashMap<Player, BukkitTask> tasks = new HashMap<>();

    private BukkitTask task;

    private Player player;
    private NRData nickData;

    public NRNickManager(Player player)
    {
        this.player = player;
        this.nickData = new AccountProvider(player.getUniqueId()).getData();
    }

    public NRNickManager() {}

    public static NRNickManager getManager()
    {
        return new NRNickManager();
    }

    public static NRNickManager getManager(Player player)
    {
        return new NRNickManager(player);
    }

    public void nick(String nick, String skin)
    {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(NickReloaded.getInstance(),
                                                                () ->
                                                                {
                                                                    if (tasks.containsKey(player))
                                                                    {
                                                                        new NRWrapperLoader().getActionbar().sendActionbar(player,
                                                                                                                           StorageManager.getInstance().getLangFile().getString(LanguageFileValues.MESSAGES_COMMANDS_NICK_ACTIVE.getValue()));
                                                                    }
                                                                },
                                                                0,
                                                                20);

        tasks.putIfAbsent(player,
                          task);

        Bukkit.getScheduler().runTaskAsynchronously(NickReloaded.getInstance(),
                                                    () ->
                                                    {
                                                        if (! players.contains(player))
                                                        {
                                                            new NRWrapperLoader().getIdentityManager().setPlayerName(player,
                                                                                                                     nick);
                                                            new NRWrapperLoader().getIdentityManager().setPlayerSkin(player,
                                                                                                                     skin);

                                                            //IF DATA NULL LOAD DATA

                                                            nickData.setName(nick);
                                                            nickData.setSkin(skin);
                                                            nickData.setNicked(true);

                                                            players.add(player);
                                                        }
                                                    });
    }

    public void unnick()
    {
        Bukkit.getScheduler().runTaskAsynchronously(NickReloaded.getInstance(),
                                                    () ->
                                                    {
                                                        if (players.contains(player))
                                                        {
                                                            new NRWrapperLoader().getIdentityManager().setPlayerName(player,
                                                                                                                     player.getName());
                                                            new NRWrapperLoader().getIdentityManager().setPlayerSkin(player,
                                                                                                                     player.getName());

                                                            stopTask(player);

                                                            nickData.setName(null);
                                                            nickData.setSkin(null);
                                                            nickData.setNicked(false);

                                                            players.remove(player);
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

    public void processData(ProcessMethod status)
    {
        if (status == ProcessMethod.DISABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player -> new AccountProvider(player.getUniqueId()).sendDataToStorage(new AccountProvider(player.getUniqueId()).getData()));
        }
        else if (status == ProcessMethod.ENABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player -> new AccountProvider(player.getUniqueId()).getData());
        }
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public enum ProcessMethod
    {
        ENABLING,
        DISABLING
    }
}
