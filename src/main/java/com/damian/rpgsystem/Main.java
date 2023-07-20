package com.damian.rpgsystem;

import com.damian.rpgsystem.events.gui.GuiEvents;
import com.damian.rpgsystem.events.player.PlayerEvents;
import com.damian.rpgsystem.extensions.player.RPGPlayer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onLoad() {
        setupData();
    }

    private static Main instance;
    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
    }

    @Override
    public void onDisable() {
        for(RPGPlayer rpgPlayer: RPGPlayer.getPlayers()) {
            rpgPlayer.saveData();
        }
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginManager().registerEvents(new GuiEvents(), this);
    }

    private void setupData() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
