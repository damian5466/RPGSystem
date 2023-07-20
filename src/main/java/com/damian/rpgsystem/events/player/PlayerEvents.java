package com.damian.rpgsystem.events.player;

import com.damian.rpgsystem.extensions.player.RPGPlayer;
import fr.xephi.authme.events.LoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    //Create a RPGPlayer instance when someone joins to server
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        RPGPlayer.create(e.getPlayer());
    }

    //Saving player data and removing him from the list when he leaves the server
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        RPGPlayer rpgPlayer = RPGPlayer.get(e.getPlayer());
        rpgPlayer.saveData();
        RPGPlayer.remove(rpgPlayer);
    }

    //Giving player his stats after successful login
    @EventHandler
    public void onLogin(LoginEvent e) {
        RPGPlayer.get(e.getPlayer()).applyStats();
    }
}
