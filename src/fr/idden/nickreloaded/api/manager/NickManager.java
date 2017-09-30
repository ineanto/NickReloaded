package fr.idden.nickreloaded.api.manager;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import fr.idden.nickreloaded.api.storage.PlayerStorage;
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
    private ConfigFile configFile = NickReloaded.get().getStorageManager().getConfigFile();
    private BukkitTask task;

    public static NickManager get()
    {
        return new NickManager();
    }

    public void nick(Player player, String nick, String skin)
    {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(NickReloaded.get(),
                                                                () ->
                                                                {
                                                                    if (tasks.containsKey(player))
                                                                    {
                                                                        if (PlayerStorage.getStorage(player.getUniqueId()).isNicked())
                                                                        {
                                                                            NickReloaded.get().getPayloadManager().getActionbar().sendActionbar(player,
                                                                                                                                                configFile.getString(Config.MESSAGES_COMMANDS_NICK_ACTIVE.getConfigValue(),
                                                                                                                                                                     true));
                                                                        }
                                                                    }
                                                                },
                                                                0,
                                                                20);
        tasks.putIfAbsent(player,
                          task);


        Bukkit.getScheduler().runTaskAsynchronously(NickReloaded.get(),
                                                    () ->
                                                    {
                                                        if (Objects.equals(nick,
                                                                           "") || nick == null || Objects.equals(skin,
                                                                                                                 "") || skin == null)
                                                        {
                                                            players.remove(player);

                                                            player.setDisplayName(player.getName());
                                                            player.setPlayerListName(player.getName());

                                                            NickReloaded.get().getPayloadManager().getIdentityManager().setPlayerName(player,
                                                                                                                                      player.getName());
                                                            NickReloaded.get().getPayloadManager().getIdentityManager().setPlayerSkin(player,
                                                                                                                                      player.getName());

                                                            stopTask(player);

                                                            if (PlayerStorage.getStorage(player.getUniqueId()) != null)
                                                            {
                                                                PlayerStorage.getStorage(player.getUniqueId()).setNick(null);
                                                                PlayerStorage.getStorage(player.getUniqueId()).setSkin(null);
                                                                PlayerStorage.getStorage(player.getUniqueId()).setNicked(false);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if (! players.contains(player))
                                                            {
                                                                players.add(player);
                                                            }

                                                            player.setDisplayName(nick);
                                                            player.setPlayerListName(nick);

                                                            NickReloaded.get().getPayloadManager().getIdentityManager().setPlayerSkin(player,
                                                                                                                                      skin);
                                                            NickReloaded.get().getPayloadManager().getIdentityManager().setPlayerName(player,
                                                                                                                                      nick);

                                                            if (PlayerStorage.getStorage(player.getUniqueId()) == null)
                                                            {
                                                                new StorageManager().load(player.getUniqueId());
                                                            }

                                                            PlayerStorage.getStorage(player.getUniqueId()).setNick(nick);
                                                            PlayerStorage.getStorage(player.getUniqueId()).setSkin(skin);
                                                            PlayerStorage.getStorage(player.getUniqueId()).setNicked(true);
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
        return PlayerStorage.getStorage(player.getUniqueId()).isNicked();
    }



    public void processData(DataStatus status)
    {
        StorageManager storageManager = NickReloaded.get().getStorageManager();

        if (status == DataStatus.DISABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player ->
                                              {
                                                  if (isNicked(player))
                                                  {
                                                      storageManager.save(player.getUniqueId());
                                                  }
                                              });
        }
        else if (status == DataStatus.ENABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player ->
                                              {
                                                  storageManager.load(player.getUniqueId());

                                                  PlayerStorage playerStorage = PlayerStorage.getStorage(player.getUniqueId());

                                                  if (isNicked(player))
                                                  {
                                                      nick(player,
                                                           playerStorage.getNick(),
                                                           playerStorage.getSkin());
                                                  }
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
