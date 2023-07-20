package com.damian.rpgsystem.events.player;

import com.damian.rpgsystem.Log;
import com.damian.rpgsystem.Main;
import com.damian.rpgsystem.extensions.player.RPGPlayer;
import fr.xephi.authme.events.LoginEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        RPGPlayer.create(e.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        RPGPlayer.get(e.getPlayer()).saveData();
        RPGPlayer.remove(RPGPlayer.get(e.getPlayer()));
    }

    @EventHandler
    public void onLogin(LoginEvent e) {
        RPGPlayer.get(e.getPlayer()).applyStats();
    }
}
