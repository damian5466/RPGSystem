package com.damian.rpgsystem;

import com.damian.rpgsystem.events.player.PlayerEvents;
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

    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
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
