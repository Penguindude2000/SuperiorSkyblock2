package com.bgsoftware.superiorskyblock.external.vanish;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.hooks.VanishProvider;
import com.bgsoftware.superiorskyblock.core.Singleton;
import com.bgsoftware.superiorskyblock.listener.PlayersListener;
import de.myzelyam.api.vanish.PlayerVanishStateChangeEvent;
import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class VanishProvider_SuperVanish implements VanishProvider, Listener {

    private static boolean alreadyEnabled = false;

    private final SuperiorSkyblockPlugin plugin;
    private final Singleton<PlayersListener> playersListener;

    public VanishProvider_SuperVanish(SuperiorSkyblockPlugin plugin) {
        this.plugin = plugin;
        this.playersListener = plugin.getListener(PlayersListener.class);

        if (!alreadyEnabled) {
            alreadyEnabled = true;
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }

        SuperiorSkyblockPlugin.log("Hooked into SuperVanish for support of vanish status of players.");
    }

    @Override
    public boolean isVanished(Player player) {
        return VanishAPI.isInvisible(player);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerVanish(PlayerVanishStateChangeEvent e) {
        if (e.isVanishing()) {
            this.playersListener.get().notifyPlayerQuit(plugin.getPlayers().getSuperiorPlayer(e.getUUID()));
        } else {
            this.playersListener.get().notifyPlayerJoin(plugin.getPlayers().getSuperiorPlayer(e.getUUID()));
        }
    }

}
