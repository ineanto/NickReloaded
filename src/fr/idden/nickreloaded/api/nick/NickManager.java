package fr.idden.nickreloaded.api.nick;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.config.Config;
import fr.idden.nickreloaded.api.config.ConfigFile;
import fr.idden.nickreloaded.api.nms.PayloadManager;
import fr.idden.nickreloaded.api.storage.PlayerStorage;
import fr.idden.nickreloaded.api.storage.StorageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class NickManager
{
    public static ArrayList<Player> players = new ArrayList<>();
    public static HashMap<Player, BukkitTask> tasks = new HashMap<>();
    private static ConfigFile configFile = StorageManager.getConfigFile();
    private static BukkitTask task;

    public static void nick(Player player, String nick, String skin)
    {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(NickReloaded.getInstance(),
                                                                () -> {
                                                                    if (tasks.containsKey(player))
                                                                    {
                                                                        if (PlayerStorage.getStorage(player.getUniqueId()).isNicked())
                                                                        {
                                                                            PayloadManager.getActionbar().sendActionbar(player,
                                                                                                                        configFile.getString(Config.MESSAGES_COMMANDS_NICK_ACTIVE.getConfigValue(),
                                                                                                                                             true));
                                                                        }
                                                                    }
                                                                },
                                                                0,
                                                                20);
        tasks.putIfAbsent(player,
                          task);


        Bukkit.getScheduler().runTaskAsynchronously(NickReloaded.getInstance(),
                                                    () -> {
                                                        if (Objects.equals(nick,
                                                                           "") || nick == null || Objects.equals(skin,
                                                                                                                 "") || skin == null)
                                                        {
                                                            players.remove(player);

                                                            player.setDisplayName(player.getName());
                                                            player.setPlayerListName(player.getName());

                                                            PayloadManager.getIdentityManager().setPlayerName(player,
                                                                                                              player.getName());
                                                            PayloadManager.getIdentityManager().setPlayerSkin(player,
                                                                                                              player.getName());

                                                            NickManager.stopTask(player);

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

                                                            PayloadManager.getIdentityManager().setPlayerSkin(player,
                                                                                                              skin);
                                                            PayloadManager.getIdentityManager().setPlayerName(player,
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

    public static void stopTask(Player player)
    {
        if (tasks.containsKey(player))
        {
            tasks.get(player).cancel();
            tasks.remove(player);
        }
    }

    public static boolean isNicked(Player player)
    {
        return PlayerStorage.getStorage(player.getUniqueId()).isNicked();
    }

    public static void processData(Status status)
    {
        StorageManager storageManager = new StorageManager();

        if (status == Status.DISABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (isNicked(player))
                {
                    storageManager.save(player.getUniqueId());
                }
            });
        }
        else if (status == Status.ENABLING)
        {
            Bukkit.getOnlinePlayers().forEach(player -> {
                storageManager.load(player.getUniqueId());

                PlayerStorage playerStorage = PlayerStorage.getStorage(player.getUniqueId());

                if (isNicked(player))
                {
                    NickManager.nick(player, playerStorage.getNick(), playerStorage.getSkin());
                }
            });
        }
    }

    public enum Status
    {
        ENABLING,
        DISABLING
    }
}
