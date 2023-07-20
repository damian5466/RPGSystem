package com.damian.rpgsystem.exceptions;

import org.bukkit.entity.Player;

public class PlayerNotFoundException extends Exception {

    private Player bukkitPlayer;

    public PlayerNotFoundException(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    @Override
    public String getMessage() {
        return "Failed to find RPGPlayer instance for player: " + bukkitPlayer.getName();
    }
}
